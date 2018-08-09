/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

public class GtnFrameworkRelationshipBuilderSearchConfigTest {

    public GtnFrameworkRelationshipBuilderSearchConfigTest() {
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
     * Test of getSearchView method, of class
     * GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());
    }

   
    /**
     * Test of addRBSearchMainPanel method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddRBSearchMainPanel() {
        try {
            System.out.println("addRBSearchMainPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addRBSearchMainPanel", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRBSearchMainLayout method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddRBSearchMainLayout() {
       try {
            System.out.println("addRBSearchMainLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addRBSearchMainLayout", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
      try {
            System.out.println("addSearchCriteriaPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addSearchCriteriaPanel", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddResultPanel() {
       try {
            System.out.println("addResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addResultPanel", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultLayout method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddResultLayout() {
      try {
            System.out.println("addResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addResultLayout", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddFieldLayout() {
        try {
            System.out.println("addFieldLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addFieldLayout", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddButtonLayout() {
       try {
            System.out.println("addButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addButtonLayout", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddFieldComponent() {
         try {
            System.out.println("addFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addFieldComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationshipName method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddRelationshipName() {
           try {
            System.out.println("addRelationshipName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addRelationshipName", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addHierarchyName method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddHierarchyName() {
        try {
            System.out.println("addHierarchyName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addHierarchyName", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationshipDescription method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddRelationshipDescription() {
         try {
            System.out.println("addRelationshipDescription");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addRelationshipDescription", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addStartDateFrom method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddStartDateFrom() {
        try {
            System.out.println("addStartDateFrom");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addStartDateFrom", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addStartDateTo method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddStartDateTo() {
          try {
            System.out.println("addStartDateTo");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addStartDateTo", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRelationshipType method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddRelationshipType() {
            try {
            System.out.println("addRelationshipType");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addRelationshipType", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCreationDateFrom method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddCreationDateFrom() {
              try {
            System.out.println("addCreationDateFrom");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addCreationDateFrom", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCreationDateTo method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddCreationDateTo() {
                  try {
            System.out.println("addCreationDateTo");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addCreationDateTo", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAuditSearchParam method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddAuditSearchParam() {
                 try {
            System.out.println("addAuditSearchParam");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addAuditSearchParam", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
              try {
            System.out.println("addSearchButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addSearchButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAuditSearchButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddAuditSearchButtonComponent() {
               try {
            System.out.println("addAuditSearchButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addAuditSearchButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPagedTableComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddPagedTableComponent() {
                 try {
            System.out.println("addPagedTableComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addPagedTableComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
           try {
            System.out.println("addActionButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addActionButtonLayout", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
     try {
            System.out.println("addResetButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAddButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddAddButtonComponent() {
   try {
            System.out.println("addAddButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addAddButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addEditButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddEditButtonComponent() {
        try {
            System.out.println("addEditButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addEditButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addViewButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddViewButtonComponent() {
      try {
            System.out.println("addViewButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addViewButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCopyButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddCopyButtonComponent() {
         try {
            System.out.println("addCopyButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addCopyButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addDeleteButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddDeleteButtonComponent() {
          try {
            System.out.println("addDeleteButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addDeleteButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkRelationshipBuilderSearchConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
            try {
            System.out.println("addExcelButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String namspacePrefix = "";
            GtnFrameworkRelationshipBuilderSearchConfig instance = new GtnFrameworkRelationshipBuilderSearchConfig();

            Method method = instance.getClass().getDeclaredMethod("addExcelButtonComponent", List.class, String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList, namspacePrefix);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRelationshipBuilderSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
