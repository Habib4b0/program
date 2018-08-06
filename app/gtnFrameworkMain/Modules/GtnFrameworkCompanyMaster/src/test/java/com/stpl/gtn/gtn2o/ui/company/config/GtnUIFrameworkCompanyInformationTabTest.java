/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnUIFrameworkCompanyInformationTabTest {
    
    public GtnUIFrameworkCompanyInformationTabTest() {
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
     * Test of addCompanyInformationTab method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyInformationTab() {
      try {
            System.out.println("addCompanyInformationTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInformationTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddFieldComponent() {
    try {
            System.out.println("addFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addFieldComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyId method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyId() {
    try {
            System.out.println("addCompanyId");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyId",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyNo method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyNo() {
      try {
            System.out.println("addCompanyNo");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyNo",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyName method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyName() {
   try {
            System.out.println("addCompanyName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyStatus method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyStatus() {
           try {
            System.out.println("addCompanyStatus");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyStatus",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyStartDate method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyStartDate() {
   try {
            System.out.println("addCompanyStartDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyStartDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyEndDate method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyEndDate() {
      try {
            System.out.println("addCompanyEndDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyEndDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyType method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyType() {
    try {
            System.out.println("addCompanyType");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyType",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyCategory method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyCategory() {
    try {
            System.out.println("addCompanyCategory");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyCategory",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyGroup method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCompanyGroup() {
        try {
            System.out.println("addCompanyGroup");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyGroup",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addOrganizationKey method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddOrganizationKey() {
         try {
            System.out.println("addOrganizationKey");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addOrganizationKey",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSource method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddSource() {
     try {
            System.out.println("addSource");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addSource",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialSystem method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddFinancialSystem() {
  try {
            System.out.println("addFinancialSystem");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addFinancialSystem",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSystemId method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddSystemId() {
         try {
            System.out.println("addSystemId");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addSystemId",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUdc1 method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddUdc1() {
        try {
            System.out.println("addUdc1");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addUdc1",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUdc2 method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddUdc2() {
        try {
            System.out.println("addUdc2");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addUdc2",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUdc3 method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddUdc3() {
        try {
            System.out.println("addUdc3");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addUdc3",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUdc4 method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddUdc4() {
     try {
            System.out.println("addUdc4");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addUdc4",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUdc5 method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddUdc5() {
       try {
            System.out.println("addUdc5");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addUdc5",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addUdc6 method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddUdc6() {
      try {
            System.out.println("addUdc6");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addUdc6",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCreatedBy method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCreatedBy() {
  try {
            System.out.println("addCreatedBy");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCreatedBy",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCreatedDate method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCreatedDate() {
         try {
            System.out.println("addCreatedDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCreatedDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addModifiedBy method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddModifiedBy() {
         try {
            System.out.println("addModifiedBy");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addModifiedBy",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addModifiedDate method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddModifiedDate() {
          try {
            System.out.println("addModifiedDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addModifiedDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCreatedByName method, of class GtnUIFrameworkCompanyInformationTab.
     */
    @Test
    public void testAddCreatedByName() {
        try {
            System.out.println("addCreatedByName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCompanyInformationTab instance = new GtnUIFrameworkCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addCreatedByName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
