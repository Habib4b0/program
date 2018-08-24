/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkRelationShipBuilderAddConfigTest {

    public GtnFrameworkRelationShipBuilderAddConfigTest() {
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
     * Test of getAddView method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testGetAddView() {
        System.out.println("getAddView");
        String viewName = "";
        GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
        GtnUIFrameworkViewConfig result = instance.getAddView(viewName);
        assertNotNull(result);
    }

    /**
     * Test of addComponentList method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddComponentList() {
        try {
            System.out.println("addComponentList");
           
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, view, namspacePrefix);
            assertFalse( view.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationShipBuilderMainPanel method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddRelationShipBuilderMainPanel() {
        try {
            System.out.println("addRelationShipBuilderMainPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();

            Method method = instance.getClass().getDeclaredMethod("addRelationShipBuilderMainPanel",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            
             assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationShipBuilderMainLayout method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddRelationShipBuilderMainLayout() {
        try {
            System.out.println("addRelationShipBuilderMainLayout");
             List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addRelationShipBuilderMainLayout",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addErrorDisplay method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddErrorDisplay() {
        try {
            System.out.println("addErrorDisplay");
             List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addErrorDisplay",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addSelectionOptionPanel method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddSelectionOptionPanel() {
        try {
            System.out.println("addSelectionOptionPanel");
             List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addSelectionOptionPanel",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultPanel method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddResultPanel() {
        try {
            System.out.println("addResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addResultPanel",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultLayout method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddResultLayout() {
         try {
            System.out.println("addResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addResultLayout",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldLayout method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddFieldLayout() {
           try {
            System.out.println("addFieldLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addFieldLayout",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddFieldComponent() {
            try {
            System.out.println("addFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addFieldComponent",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationshipName method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddRelationshipName() {
        try {
            System.out.println("addRelationshipName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addRelationshipName",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationshipDescription method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddRelationshipDescription() {
        try {
            System.out.println("addRelationshipDescription");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addRelationshipDescription",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addHierarchyName method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddHierarchyName() {
        try {
            System.out.println("addHierarchyName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addHierarchyName",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationshipType method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddRelationshipType() {
      try {
            System.out.println("addRelationshipType");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addRelationshipType",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addVersionNo method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddVersionNo() {
      try {
            System.out.println("addVersionNo");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addVersionNo",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addStartDate method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddStartDate() {
        try {
            System.out.println("addStartDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addStartDate",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addBuildType method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddBuildType() {
     try {
            System.out.println("addBuildType");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addBuildType",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addActionButtonLayout method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        try {
            System.out.println("addActionButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addActionButtonLayout",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addBackButtonComponent method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddBackButtonComponent() {
           try {
            System.out.println("addBackButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addBackButtonComponent",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSaveButtonComponent method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddSaveButtonComponent() {
           try {
            System.out.println("addSaveButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addSaveButtonComponent",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class
     * GtnFrameworkRelationShipBuilderAddConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
                try {
            System.out.println("addResetButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationShipBuilderAddConfig instance = new GtnFrameworkRelationShipBuilderAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationShipBuilderAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
