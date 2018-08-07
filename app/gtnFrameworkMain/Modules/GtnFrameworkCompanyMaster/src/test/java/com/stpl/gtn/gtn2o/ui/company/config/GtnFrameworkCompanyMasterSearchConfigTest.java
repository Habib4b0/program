/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
public class GtnFrameworkCompanyMasterSearchConfigTest {
    
    public GtnFrameworkCompanyMasterSearchConfigTest() {
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
     * Test of getSearchView method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, view);
            assertFalse( view.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCriteriaPanel method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCriteriaPanel() {
        try {
            System.out.println("addCMSearchCriteriaPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCriteriaPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMResultPanel method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMResultPanel() {
        try {
            System.out.println("addCMResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMResultPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMResultLayout method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMResultLayout() {
        try {
            System.out.println("addCMResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMResultLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMFieldLayout method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMFieldLayout() {
    try {
            System.out.println("addCMFieldLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMFieldLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMButtonLayout method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMButtonLayout() {
    try {
            System.out.println("addCMButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMFieldComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMFieldComponent() {
    try {
            System.out.println("addCMFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMFieldComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchGeneralSearcField method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchGeneralSearcField() {
//          try {
//        System.out.println("addCMSearchGeneralSearcField");
//        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<GtnUIFrameworkComponentConfig>();
//        String componentId = "";
//        String componentName = "";
//        GtnUIFrameworkComponentType componentType = null;
//        GtnUIFrameworkConditionalValidationType validationType = null;
//        GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
//        instance.addCMSearchGeneralSearcField(componentList, componentId, componentName, componentType, validationType);
//        assertFalse(componentList.size()!=2);
//           } catch (Exception ex) {
//            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
//        }

       try {
            System.out.println("addCMSearchGeneralSearcField");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String componentId="";
            String componentName="";
            GtnUIFrameworkComponentType componentType=null;
            GtnUIFrameworkConditionalValidationType validationType=null;
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchGeneralSearcField",List.class,String.class,String.class,GtnUIFrameworkComponentType.class,GtnUIFrameworkConditionalValidationType.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentId,componentName,componentType,validationType);
            assertFalse( componentList.size()!=2);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCompanyStatus method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCompanyStatus() {
         try {
            System.out.println("addCMSearchCompanyStatus");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCompanyStatus",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCompanyType method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCompanyType() {
        try {
            System.out.println("addCMSearchCompanyType");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCompanyType",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCompanyCategory method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCompanyCategory() {
       try {
            System.out.println("addCMSearchCompanyCategory");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCompanyCategory",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCompanyGroup method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCompanyGroup() {
    try {
            System.out.println("addCMSearchCompanyGroup");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCompanyGroup",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCompanyTradeClass method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCompanyTradeClass() {
        try {
            System.out.println("addCMSearchCompanyTradeClass");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCompanyTradeClass",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchCompanyQualifierName method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchCompanyQualifierName() {
     try {
            System.out.println("addCMSearchCompanyQualifierName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchCompanyQualifierName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMSearchButtonComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMSearchButtonComponent() {
    try {
            System.out.println("addCMSearchButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMSearchButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMResetButtonComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMResetButtonComponent() {
    try {
            System.out.println("addCMResetButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMResetButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMPagedTableComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMPagedTableComponent() {
        try {
            System.out.println("addCMPagedTableComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMPagedTableComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCMActionButtonLayout method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddCMActionButtonLayout() {
        try {
            System.out.println("addCMActionButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addCMActionButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAddButtonComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddAddButtonComponent() {
        try {
            System.out.println("addAddButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addAddButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addEditButtonComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddEditButtonComponent() {
    try {
            System.out.println("addEditButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addEditButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addViewButtonComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddViewButtonComponent() {
    try {
            System.out.println("addViewButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addViewButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkCompanyMasterSearchConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
    try {
            System.out.println("addExcelButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterSearchConfig instance = new GtnFrameworkCompanyMasterSearchConfig();
            Method method = instance.getClass().getDeclaredMethod("addExcelButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
