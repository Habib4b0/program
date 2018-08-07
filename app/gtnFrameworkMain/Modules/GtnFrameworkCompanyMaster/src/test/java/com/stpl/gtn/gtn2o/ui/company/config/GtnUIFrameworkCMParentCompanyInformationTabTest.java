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
public class GtnUIFrameworkCMParentCompanyInformationTabTest {
    
    public GtnUIFrameworkCMParentCompanyInformationTabTest() {
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
     * Test of addParentCompanyTab method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testAddParentCompanyTab() {
        try {
            System.out.println("addParentCompanyTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addParentCompanyTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyInformationPanel method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyInformationPanel() {
        try {
            System.out.println("parentCompanyInformationPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyInformationPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyInformationLayout method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyInformationLayout() {
        try {
            System.out.println("parentCompanyInformationLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyInformationLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyInformationFields method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyInformationFields() {
    try {
            System.out.println("parentCompanyInformationFields");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyInformationFields",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of addParentCompanyName method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testAddParentCompanyName() {
         try {
            System.out.println("addParentCompanyName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addParentCompanyName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addParentCompanyStartDate method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testAddParentCompanyStartDate() {
         try {
            System.out.println("addParentCompanyStartDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addParentCompanyStartDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addParentCompanyEndDate method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testAddParentCompanyEndDate() {
        try {
            System.out.println("addParentCompanyEndDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addParentCompanyEndDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyInformationAttachButtonLayout method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyInformationAttachButtonLayout() {
      try {
            System.out.println("parentCompanyInformationAttachButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyInformationAttachButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyInformationAttachButton method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyInformationAttachButton() {
    try {
            System.out.println("parentCompanyInformationAttachButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyInformationAttachButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyResultPanel method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyResultPanel() {
        try {
            System.out.println("parentCompanyResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyResultPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of parentCompanyResultLayout method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyResultLayout() {
        try {
            System.out.println("parentCompanyResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyResultLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    /**
     * Test of parentCompanyInformationRemoveButtonLayout method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testParentCompanyInformationRemoveButtonLayout() {
        try {
            System.out.println("parentCompanyInformationRemoveButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("parentCompanyInformationRemoveButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of addExcelButtonComponent method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testAddExcelButtonComponent() {
        try {
            System.out.println("addExcelButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addExcelButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addParentCompanyMasterSid method, of class GtnUIFrameworkCMParentCompanyInformationTab.
     */
    @Test
    public void testAddParentCompanyMasterSid() {
        try {
            System.out.println("addParentCompanyMasterSid");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyInformationTab instance = new GtnUIFrameworkCMParentCompanyInformationTab();
            Method method = instance.getClass().getDeclaredMethod("addParentCompanyMasterSid",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyInformationTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
