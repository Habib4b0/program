/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;


import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
public class GtnFrameworkRelationshipBuilderResultLayoutConfigTest {
    
    public GtnFrameworkRelationshipBuilderResultLayoutConfigTest() {
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
     * Test of addResultLayout method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAddResultLayout() {
        System.out.println("addResultLayout");
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        String namespaceprefix = "";
        boolean isView = false;
        GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
        instance.addResultLayout(componentList, namespaceprefix, isView);
        assertFalse("No component Added",0==componentList.size());
    }

    /**
     * Test of addHierarchyLevelPanel method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAddHierarchyLevelPanel() {
        System.out.println("addHierarchyLevelPanel");
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        String namespaceprefix = "";
        boolean isView = false;
        GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
        instance.addHierarchyLevelPanel(componentList, namespaceprefix, isView);
         assertFalse("No component Added",0==componentList.size());
    }

    /**
     * Test of addRelationshipTree method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAddRelationshipTree() {
        System.out.println("addRelationshipTree");
        List<GtnUIFrameworkComponentConfig> componentList  = new ArrayList<>();
        String namespaceprefix = "";
        boolean isView = false;
        GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
        instance.addRelationshipTree(componentList, namespaceprefix, isView);
        assertFalse("No component Added",0==componentList.size());
    }

    /**
     * Test of availableResultPanel method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAvailableResultPanel() {
            try {
            System.out.println("availableResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
            Method method = instance.getClass().getDeclaredMethod("availableResultPanel",List.class,String.class,Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix,false);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderResultLayoutConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAddToTreeButtonComponent method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAddAddToTreeButtonComponent() {
          try {
            System.out.println("addAddToTreeButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
            Method method = instance.getClass().getDeclaredMethod("addAddToTreeButtonComponent",List.class,String.class,Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix,false);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderResultLayoutConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAutoBuildButtonComponent method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAddAutoBuildButtonComponent() {
             try {
            System.out.println("addAutoBuildButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
            Method method = instance.getClass().getDeclaredMethod("addAutoBuildButtonComponent",List.class,String.class,Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix,false);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderResultLayoutConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of availableResultDataTable method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAvailableResultDataTable() {
              try {
            System.out.println("availableResultDataTable");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
            Method method = instance.getClass().getDeclaredMethod("availableResultDataTable",List.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderResultLayoutConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRemoveFromTreeButtonComponent method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testAddRemoveFromTreeButtonComponent() {
                try {
            System.out.println("addRemoveFromTreeButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
            Method method = instance.getClass().getDeclaredMethod("addRemoveFromTreeButtonComponent",List.class,String.class,Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix,false);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderResultLayoutConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getItemClickAction method, of class GtnFrameworkRelationshipBuilderResultLayoutConfig.
     */
    @Test
    public void testGetItemClickAction() {
              try {
            System.out.println("getItemClickAction");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderResultLayoutConfig instance = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
            Method method = instance.getClass().getDeclaredMethod("getItemClickAction",String.class);
            method.setAccessible(true);
            GtnUIFrameWorkActionConfig out=(GtnUIFrameWorkActionConfig) method.invoke(instance, namspacePrefix);
            assertFalse( out.getActionParameterList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderResultLayoutConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
