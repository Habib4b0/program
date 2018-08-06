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
public class GtnUIFrameworkCMAdderssTabConfigTest {
    
    public GtnUIFrameworkCMAdderssTabConfigTest() {
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
     * Test of addAddressTab method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddAddressTab() {
         try {
            System.out.println("addAddressTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addAddressTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddFieldComponent() {
       try {
            System.out.println("addFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addFieldComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAdderss1 method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddAdderss1() {
        try {
            System.out.println("addAdderss1");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addAdderss1",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCity method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddCity() {
        try {
            System.out.println("addCity");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addCity",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addZipCode method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddZipCode() {
    try {
            System.out.println("addZipCode");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addZipCode",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCountry method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddCountry() {
        try {
            System.out.println("addCountry");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addCountry",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAddress2 method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddAddress2() {
    try {
            System.out.println("addAddress2");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addAddress2",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addState method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddState() {
    try {
            System.out.println("addState");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addState",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addRegionCode method, of class GtnUIFrameworkCMAdderssTabConfig.
     */
    @Test
    public void testAddRegionCode() {
        try {
            System.out.println("addRegionCode");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMAdderssTabConfig instance = new GtnUIFrameworkCMAdderssTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addRegionCode",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMAdderssTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
