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
public class GtnFrameWorkCompanyMasterFinancialCloseConfigTest {
    
    public GtnFrameWorkCompanyMasterFinancialCloseConfigTest() {
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


    @Test
    public void testAddFinancialCloseTab() {
       try {
            System.out.println("addFinancialCloseTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseInformationPanel method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseInformationPanel() {

        try {
            System.out.println("financialCloseInformationPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseInformationPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseInformationLayout method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseInformationLayout() {

        try {
            System.out.println("financialCloseInformationLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseInformationLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseInformationFields method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseInformationFields() {
    try {
            System.out.println("financialCloseInformationFields");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseInformationFields",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseMode method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseMode() {
        try {
            System.out.println("addFinancialCloseMode");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseMode",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseMonth method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseMonth() {
    try {
            System.out.println("addFinancialCloseMonth");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseMonth",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseBusinessDay method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseBusinessDay() {
    try {
            System.out.println("addFinancialCloseBusinessDay");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseBusinessDay",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseBusinessHour method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseBusinessHour() {
        try {
            System.out.println("addFinancialCloseBusinessHour");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseBusinessHour",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseMinute method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseMinute() {
    try {
            System.out.println("addFinancialCloseMinute");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseMinute",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseOpenButtonLayout method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseOpenButtonLayout() {
    try {
            System.out.println("financialCloseOpenButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseOpenButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseCloseButton method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseCloseButton() {
        try {
            System.out.println("financialCloseCloseButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseCloseButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseOpenButton method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseOpenButton() {
    try {
            System.out.println("financialCloseOpenButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseOpenButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseResetButton method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseResetButton() {
        try {
            System.out.println("financialCloseResetButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseResetButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseResultPanel method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseResultPanel() {
        try {
            System.out.println("financialCloseResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseResultPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseResultLayout method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseResultLayout() {
        try {
            System.out.println("financialCloseResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseResultLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of financialCloseResultPagedTable method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testFinancialCloseResultPagedTable() {
        try {
            System.out.println("financialCloseResultPagedTable");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("financialCloseResultPagedTable",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseCalendar method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseCalendar() {
    try {
            System.out.println("addFinancialCloseCalendar");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseCalendar",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseYear method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseYear() {
    try {
            System.out.println("addFinancialCloseYear");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseYear",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseUserId method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseUserId() {
        try {
            System.out.println("addFinancialCloseUserId");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseUserId",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFinancialCloseSessionId method, of class GtnFrameWorkCompanyMasterFinancialCloseConfig.
     */
    @Test
    public void testAddFinancialCloseSessionId() {
        try {
            System.out.println("addFinancialCloseSessionId");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameWorkCompanyMasterFinancialCloseConfig instance = new GtnFrameWorkCompanyMasterFinancialCloseConfig();
            Method method = instance.getClass().getDeclaredMethod("addFinancialCloseSessionId",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameWorkCompanyMasterFinancialCloseConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
