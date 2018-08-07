/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
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
public class GtnFrameworkCMIdentifierTabTest {
    
    public GtnFrameworkCMIdentifierTabTest() {
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
     * Test of addIdentifierTab method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddIdentifierTab() {
        try {
            System.out.println("addIdentifierTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addIdentifierTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierInformationPanel method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationPanel() {
        try {
            System.out.println("identifierInformationPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierInformationLayout method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationLayout() {
        try {
            System.out.println("identifierInformationLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierInformationFields method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationFields() {
        try {
            System.out.println("identifierInformationFields");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationFields",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyQualifierName method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddCompanyQualifierName() {
       try {
            System.out.println("addCompanyQualifierName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyQualifierName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyIdentifier method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddCompanyIdentifier() {
    try {
            System.out.println("addCompanyIdentifier");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyIdentifier",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyIdentifierStartDate method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddCompanyIdentifierStartDate() {
        try {
            System.out.println("addCompanyIdentifierStartDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyIdentifierStartDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyIdentifierEndDate method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddCompanyIdentifierEndDate() {
         try {
            System.out.println("addCompanyIdentifierEndDate");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyIdentifierEndDate",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyStatus method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddCompanyStatus() {
        try {
            System.out.println("addCompanyStatus");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addCompanyStatus",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierInformationAttachButtonLayout method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationAttachButtonLayout() {
        try {
            System.out.println("identifierInformationAttachButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationAttachButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierInformationAttachButton method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationAttachButton() {
        try {
            System.out.println("identifierInformationAttachButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationAttachButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierResultPanel method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierResultPanel() {
        try {
            System.out.println("identifierResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierResultPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierResultLayout method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierResultLayout() {
        try {
            System.out.println("identifierResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierResultLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierResultDataTable method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierResultDataTable() {
    try {
            System.out.println("identifierResultDataTable");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierResultDataTable",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createTableFieldFactoryComponents method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testCreateTableFieldFactoryComponents() {
         try {
            System.out.println("createTableFieldFactoryComponents");
            List<String > propertyIds = GtnFrameworkCompanyStringContants.getCmIdentifierTextfieldPropertiesList();
            List<GtnUIFrameworkComponentConfig> expResult = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("createTableFieldFactoryComponents",List.class);
            method.setAccessible(true);
            List<GtnUIFrameworkComponentConfig> result=(List<GtnUIFrameworkComponentConfig>)  method.invoke(instance, propertyIds);
            assertEquals( result.size(),propertyIds.size());
            } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of identifierInformationRemoveButtonLayout method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationRemoveButtonLayout() {
    try {
            System.out.println("identifierInformationRemoveButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationRemoveButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierInformationRemoveButton method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testIdentifierInformationRemoveButton() {
    try {
            System.out.println("identifierInformationRemoveButton");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("identifierInformationRemoveButton",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkCMIdentifierTab.
     */
    @Test
    public void testAddExcelButtonComponent() {
        try {
            System.out.println("addExcelButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCMIdentifierTab instance = new GtnFrameworkCMIdentifierTab();
            Method method = instance.getClass().getDeclaredMethod("addExcelButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCMIdentifierTabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
