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
public class GtnUIFrameworkCMTradeClassTabTest {
    
    public GtnUIFrameworkCMTradeClassTabTest() {
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
     * Test of addTradeClassTab method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testAddTradeClassTab() {
        try {
            System.out.println("addTradeClassTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("addTradeClassTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassInformationPanel method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationPanel() {
        try {
            System.out.println("tradeClassInformationPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassInformationLayout method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationLayout() {
        try {
            System.out.println("tradeClassInformationLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassInformationFields method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationFields() {
      try {
            System.out.println("tradeClassInformationFields");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationFields",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTradeClass method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testAddTradeClass() {
       try {
            System.out.println("addTradeClass");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("addTradeClass",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTradeClassStartDate method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testAddTradeClassStartDate() {
       try {
            System.out.println("addTradeClassStartDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("addTradeClassStartDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTradeClassEndDate method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testAddTradeClassEndDate() {
    try {
            System.out.println("addTradeClassEndDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("addTradeClassEndDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassInformationAttachButtonLayout method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationAttachButtonLayout() {
          try {
            System.out.println("tradeClassInformationAttachButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationAttachButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassInformationAttachButton method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationAttachButton() {
        try {
            System.out.println("tradeClassInformationAttachButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationAttachButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassResultPanel method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassResultPanel() {
          try {
            System.out.println("tradeClassResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassResultPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassResultLayout method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassResultLayout() {
         try {
            System.out.println("tradeClassResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassResultLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassResultDataTable method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassResultDataTable() {
 try {
            System.out.println("tradeClassResultDataTable");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassResultDataTable",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of tradeClassInformationRemoveButtonLayout method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationRemoveButtonLayout() {
       try {
            System.out.println("tradeClassInformationRemoveButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationRemoveButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of tradeClassInformationRemoveButton method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testTradeClassInformationRemoveButton() {
       try {
            System.out.println("tradeClassInformationRemoveButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("tradeClassInformationRemoveButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnUIFrameworkCMTradeClassTab.
     */
    @Test
    public void testAddExcelButtonComponent() {
        try {
            System.out.println("addExcelButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMTradeClassTab instance = new GtnUIFrameworkCMTradeClassTab();
            Method method = instance.getClass().getDeclaredMethod("addExcelButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMTradeClassTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
