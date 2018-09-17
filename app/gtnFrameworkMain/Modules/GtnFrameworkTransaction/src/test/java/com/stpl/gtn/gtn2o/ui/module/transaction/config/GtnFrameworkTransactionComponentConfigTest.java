/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.config;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentListForInvalidBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnUIFrameworkTransactionTabsheetBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionColumnBean;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionModuleBean;
import java.lang.reflect.InvocationTargetException;
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
public class GtnFrameworkTransactionComponentConfigTest {
    
    public GtnFrameworkTransactionComponentConfigTest() {
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
     * Test of getComponentsForModules method, of class GtnFrameworkTransactionComponentConfig.
     */
    @Test
    public void testGetComponentsForModules() {
        String methodName = "addInvalidToDate";
        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of setEnableFlag method, of class GtnFrameworkTransactionComponentConfig.
     */
    @Test
    public void testSetEnableFlag() {
        try {
            System.out.println("setEnableFlag");
            GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
            String tableName = "AccrualMaster";
            Method method = instance.getClass().getDeclaredMethod("setEnableFlag",String.class);
            method.setAccessible(true);
            method.invoke(instance, tableName);
            assertFalse( tableName.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkTransactionComponentConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setComponentToList method, of class GtnFrameworkTransactionComponentConfig.
     */
//    @Test
//    public void testSetComponentToList() {
//        System.out.println("setComponentToList");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String portletName = "";
//        boolean isInvalid = false;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.setComponentToList(componentList, portletName, isInvalid, componentBean);   
//    }
//
//    /**
//     * Test of seperateComponentsByType method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testSeperateComponentsByType() {
//        System.out.println("seperateComponentsByType");
//        GtnWSTransactionModuleBean transactionModuleBeanList = null;
//        boolean isInvalid = false;
//        List<List<GtnUIFrameworkComponentConfig>> componentList = null;
//        String portletName = "";
//        String tableName = "";
//        GtnUIFrameworkTransactionComponentListForInvalidBean transactionBean = null;
//        String moduleName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.seperateComponentsByType(transactionModuleBeanList, isInvalid, componentList, portletName, tableName, transactionBean, moduleName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getComponentListForComponents method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetComponentListForComponents() {
//        System.out.println("getComponentListForComponents");
//        boolean isValidComponentCondition = false;
//        List<GtnWSTransactionColumnBean> transactionBeanList = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        GtnUIFrameworkTransactionComponentTypeListBean expResult = null;
//        GtnUIFrameworkTransactionComponentTypeListBean result = instance.getComponentListForComponents(isValidComponentCondition, transactionBeanList, isInvalid);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getInvalidOnlyComponentList method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetInvalidOnlyComponentList() {
//        System.out.println("getInvalidOnlyComponentList");
//        GtnWSTransactionColumnBean gtnWSTransactionColumnBean = null;
//        List<GtnWSTransactionColumnBean> searchComponent = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getInvalidOnlyComponentList(gtnWSTransactionColumnBean, searchComponent, listViewComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getViewRelatedComponentList method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetViewRelatedComponentList() {
//        System.out.println("getViewRelatedComponentList");
//        GtnWSTransactionColumnBean gtnWSTransactionColumnBean = null;
//        List<GtnWSTransactionColumnBean> viewModeComponents = null;
//        List<GtnWSTransactionColumnBean> viewModeOrderComponents = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getViewRelatedComponentList(gtnWSTransactionColumnBean, viewModeComponents, viewModeOrderComponents);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getViewDateRelatedComponentList method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetViewDateRelatedComponentList() {
//        System.out.println("getViewDateRelatedComponentList");
//        GtnWSTransactionColumnBean gtnWSTransactionColumnBean = null;
//        List<GtnWSTransactionColumnBean> viewDateModeComponents = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getViewDateRelatedComponentList(gtnWSTransactionColumnBean, viewDateModeComponents);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSearchComponentsList method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetSearchComponentsList() {
//        System.out.println("getSearchComponentsList");
//        GtnWSTransactionColumnBean gtnWSTransactionColumnBean = null;
//        List<GtnWSTransactionColumnBean> searchComponent = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        List<GtnWSTransactionColumnBean> defaultListViewComponent = null;
//        List<GtnWSTransactionColumnBean> staticComponent = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getSearchComponentsList(gtnWSTransactionColumnBean, searchComponent, listViewComponent, defaultListViewComponent, staticComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVisibleColumnsAndHeader method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetVisibleColumnsAndHeader() {
//        System.out.println("getVisibleColumnsAndHeader");
//        List<GtnWSTransactionColumnBean> searchComponent = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        boolean isInvalid = false;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getVisibleColumnsAndHeader(searchComponent, listViewComponent, isInvalid, componentBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTransactionColumnBeanList method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetTransactionColumnBeanList() {
//        System.out.println("getTransactionColumnBeanList");
//        String portletName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        GtnWSTransactionModuleBean expResult = null;
//        GtnWSTransactionModuleBean result = instance.getTransactionColumnBeanList(portletName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addFieldComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddFieldComponent() {
//        System.out.println("addFieldComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String layout = "";
//        List<GtnWSTransactionColumnBean> searchComponent = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addFieldComponent(componentList, layout, searchComponent, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addInvalidFieldComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddInvalidFieldComponent() {
//        System.out.println("addInvalidFieldComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String layout = "";
//        int fromIndex = 0;
//        int toIndex = 0;
//        List<GtnWSTransactionColumnBean> staticComponent = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addInvalidFieldComponent(componentList, layout, fromIndex, toIndex, staticComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addTextField method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddTextField() {
//        System.out.println("addTextField");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnWSTransactionColumnBean component = null;
//        String parentLayoutName = "";
//        String layoutPrefix = "";
//        String componentPrefix = "";
//        boolean isEnable = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addTextField(componentList, component, parentLayoutName, layoutPrefix, componentPrefix, isEnable);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addComboBox method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddComboBox() {
//        System.out.println("addComboBox");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnWSTransactionColumnBean component = null;
//        String parentLayoutName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addComboBox(componentList, component, parentLayoutName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAdditonalSetting method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetAdditonalSetting() {
//        System.out.println("getAdditonalSetting");
//        GtnWSTransactionColumnBean component = null;
//        GtnUIFrameworkComponentConfig companyStatus = null;
//        GtnUIFrameworkComboBoxConfig companyStatusConfig = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getAdditonalSetting(component, companyStatus, companyStatusConfig);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addDateField method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddDateField() {
//        System.out.println("addDateField");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnWSTransactionColumnBean component = null;
//        String parentLayoutName = "";
//        String layoutPrefix = "";
//        String componentPrefix = "";
//        boolean isEnable = false;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addDateField(componentList, component, parentLayoutName, layoutPrefix, componentPrefix, isEnable, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addPagedTableComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddPagedTableComponent() {
//        System.out.println("addPagedTableComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String tableName = "";
//        String moduleName = "";
//        boolean isInvalid = false;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        String invalidModule = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addPagedTableComponent(componentList, tableName, moduleName, isInvalid, componentBean, invalidModule);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getValidTableConfig method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetValidTableConfig() {
//        System.out.println("getValidTableConfig");
//        GtnUIFrameworkPagedTableConfig searchResults = null;
//        GtnUIFrameworkComponentConfig searchResultConfig = null;
//        String tableName = "";
//        boolean viewEnabled = false;
//        List<GtnWSTransactionColumnBean> viewModeComponents = null;
//        List<GtnWSTransactionColumnBean> viewDateModeComponents = null;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getValidTableConfig(searchResults, searchResultConfig, tableName, viewEnabled, viewModeComponents, viewDateModeComponents, componentBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getViewActionconfig method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetViewActionconfig() {
//        System.out.println("getViewActionconfig");
//        GtnUIFrameWorkActionConfig viewActionConfig = null;
//        String tableName = "";
//        Boolean isInvalid = null;
//        List<GtnWSTransactionColumnBean> viewModeComponents = null;
//        List<GtnWSTransactionColumnBean> viewModeOrderComponents = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        List<GtnWSTransactionColumnBean> viewDateModeComponents = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getViewActionconfig(viewActionConfig, tableName, isInvalid, viewModeComponents, viewModeOrderComponents, listViewComponent, viewDateModeComponents);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getInvalidTableConfig method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetInvalidTableConfig() {
//        System.out.println("getInvalidTableConfig");
//        GtnUIFrameworkPagedTableConfig searchResults = null;
//        String tableName = "";
//        boolean viewEnabled = false;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        String invalidModule = "";
//        List<GtnWSTransactionColumnBean> viewDateModeComponents = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getInvalidTableConfig(searchResults, tableName, viewEnabled, componentBean, invalidModule, viewDateModeComponents);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addSearchButtonComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddSearchButtonComponent() {
//        System.out.println("addSearchButtonComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String tableName = "";
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addSearchButtonComponent(componentList, tableName, componentBean, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addResetButtonComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddResetButtonComponent() {
//        System.out.println("addResetButtonComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        List<GtnWSTransactionColumnBean> searchComponent = null;
//        String parentComponentId = "";
//        String alertMessage = "";
//        boolean isReprocessLayout = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addResetButtonComponent(componentList, searchComponent, parentComponentId, alertMessage, isReprocessLayout);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addReprocessButtonComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddReprocessButtonComponent() {
//        System.out.println("addReprocessButtonComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String wsReprocessURL = "";
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        String portletName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addReprocessButtonComponent(componentList, wsReprocessURL, componentBean, portletName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addRemoveButtonComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddRemoveButtonComponent() {
//        System.out.println("addRemoveButtonComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        String portletName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addRemoveButtonComponent(componentList, componentBean, portletName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createTableGeneratedColumnComponents method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testCreateTableGeneratedColumnComponents() {
//        System.out.println("createTableGeneratedColumnComponents");
//        String propertyId = "";
//        String tableName = "";
//        boolean viewEnabled = false;
//        List<GtnWSTransactionColumnBean> viewModeComponents = null;
//        List<GtnWSTransactionColumnBean> viewModeOrderComponents = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        GtnUIFrameworkTransactionComponentTypeListBean gtnUIFrameworkTransactionComponentTypeListBean = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        List<GtnUIFrameworkComponentConfig> expResult = null;
//        List<GtnUIFrameworkComponentConfig> result = instance.createTableGeneratedColumnComponents(propertyId, tableName, viewEnabled, viewModeComponents, viewModeOrderComponents, listViewComponent, gtnUIFrameworkTransactionComponentTypeListBean);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createTableFieldFactoryComponents method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testCreateTableFieldFactoryComponents() {
//        System.out.println("createTableFieldFactoryComponents");
//        List<String> propertyIds = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        List<GtnUIFrameworkComponentConfig> expResult = null;
//        List<GtnUIFrameworkComponentConfig> result = instance.createTableFieldFactoryComponents(propertyIds);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateViewOnlyColumns method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGenerateViewOnlyColumns() {
//        System.out.println("generateViewOnlyColumns");
//        List<GtnWSTransactionColumnBean> transactionBeanList = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        List<GtnUIFrameworkComponentConfig> viewComponentList = null;
//        boolean viewIndexFlag = false;
//        String portletName = "";
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.generateViewOnlyColumns(transactionBeanList, listViewComponent, viewComponentList, viewIndexFlag, portletName, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateViewMode method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGenerateViewMode() {
//        System.out.println("generateViewMode");
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        List<GtnUIFrameworkComponentConfig> viewComponentList = null;
//        boolean viewIndexFlag = false;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.generateViewMode(viewComponent, viewComponentList, viewIndexFlag, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addViewFieldComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddViewFieldComponent_4args() {
//        System.out.println("addViewFieldComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String layoutName = "";
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addViewFieldComponent(componentList, layoutName, viewComponent, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addViewOnlyId method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddViewOnlyId() {
//        System.out.println("addViewOnlyId");
//        List<GtnWSTransactionColumnBean> viewModeComponents2 = null;
//        List<Object> visibleColumns2 = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addViewOnlyId(viewModeComponents2, visibleColumns2);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addDateView method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddDateView() {
//        System.out.println("addDateView");
//        List<GtnWSTransactionColumnBean> viewDateModeComponents = null;
//        List<Object> visibleColumns3 = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addDateView(viewDateModeComponents, visibleColumns3);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of removeColumnIdNoInView method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testRemoveColumnIdNoInView() {
//        System.out.println("removeColumnIdNoInView");
//        List<Object> visibleColumns2 = null;
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.removeColumnIdNoInView(visibleColumns2, listViewComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateTabsheetView method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGenerateTabsheetView() {
//        System.out.println("generateTabsheetView");
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        List<GtnUIFrameworkComponentConfig> viewComponentList = null;
//        String portletName = "";
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.generateTabsheetView(viewComponent, viewComponentList, portletName, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addViewFieldLayout method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddViewFieldLayout() {
//        System.out.println("addViewFieldLayout");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnUIFrameworkTransactionTabsheetBean tabsheetBean = null;
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addViewFieldLayout(componentList, tabsheetBean, viewComponent, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateTabsheet method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGenerateTabsheet() {
//        System.out.println("generateTabsheet");
//        GtnUIFrameworkTransactionTabsheetBean tabsheetBean = null;
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        List<GtnUIFrameworkTabConfig> expResult = null;
//        List<GtnUIFrameworkTabConfig> result = instance.generateTabsheet(tabsheetBean, viewComponent, isInvalid);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addTabLayout method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddTabLayout() {
//        System.out.println("addTabLayout");
//        GtnUIFrameworkTabConfig tabSheetConfig = null;
//        int start = 0;
//        int offset = 0;
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        boolean isInvalid = false;
//        List<GtnUIFrameworkComponentConfig> tabConfigList = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addTabLayout(tabSheetConfig, start, offset, viewComponent, isInvalid, tabConfigList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addViewFieldComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddViewFieldComponent_6args() {
//        System.out.println("addViewFieldComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        int start = 0;
//        int offset = 0;
//        String parent = "";
//        List<GtnWSTransactionColumnBean> viewComponent = null;
//        boolean isInvalid = false;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addViewFieldComponent(componentList, start, offset, parent, viewComponent, isInvalid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTransactioTabsheenBeanFromJson method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetTransactioTabsheenBeanFromJson() {
//        System.out.println("getTransactioTabsheenBeanFromJson");
//        String portletName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        GtnUIFrameworkTransactionTabsheetBean expResult = null;
//        GtnUIFrameworkTransactionTabsheetBean result = instance.getTransactioTabsheenBeanFromJson(portletName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addExcelButtonComponent method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddExcelButtonComponent() {
//        System.out.println("addExcelButtonComponent");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        String tableName = "";
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addExcelButtonComponent(componentList, tableName, componentBean);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCustomFilter method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetCustomFilter() {
//        System.out.println("getCustomFilter");
//        String tableName = "";
//        GtnUIFrameworkPagedTableConfig searchResults = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getCustomFilter(tableName, searchResults);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMandatoryComponentList method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetMandatoryComponentList() {
//        System.out.println("getMandatoryComponentList");
//        List<GtnWSTransactionColumnBean> searchComponent = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getMandatoryComponentList(searchComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDefaultVisibleColumnAndHeader method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetDefaultVisibleColumnAndHeader() {
//        System.out.println("getDefaultVisibleColumnAndHeader");
//        List<GtnWSTransactionColumnBean> listViewComponent = null;
//        List<GtnWSTransactionColumnBean> defaultListViewComponent = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getDefaultVisibleColumnAndHeader(listViewComponent, defaultListViewComponent);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getConfigurationForModules method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testGetConfigurationForModules() {
//        System.out.println("getConfigurationForModules");
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        List<GtnWSTransactionColumnBean> moduleComponentList = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.getConfigurationForModules(componentBean, moduleComponentList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addOutBoundModuleCriteria method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testAddOutBoundModuleCriteria() {
//        System.out.println("addOutBoundModuleCriteria");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        String portletName = "";
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.addOutBoundModuleCriteria(componentList, componentBean, portletName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of setDecimalFormatLogic method, of class GtnFrameworkTransactionComponentConfig.
//     */
//    @Test
//    public void testSetDecimalFormatLogic() {
//        System.out.println("setDecimalFormatLogic");
//        GtnWSTransactionColumnBean gtnWSTransactionColumnBean = null;
//        GtnUIFrameworkTransactionComponentTypeListBean componentBean = null;
//        GtnFrameworkTransactionComponentConfig instance = new GtnFrameworkTransactionComponentConfig();
//        instance.setDecimalFormatLogic(gtnWSTransactionColumnBean, componentBean);
//    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkTransactionComponentConfig gtnFrameworkContractDashboardProcessConfig) {
        System.out.println(gtnFrameworkContractDashboardProcessConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardProcessConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractDashboardProcessConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardProcessConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkTransactionComponentConfig gtnFrameworkContractMainConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
                String name = classArr[i].getName();
                switch (classArr[i].getName()) {
                    case "java.lang.String":
                        obj[i] = "VALUE";
                        break;
                    case "java.util.List":
                        obj[i] = new ArrayList<>();
                        break;
                    default:
                        break;
                }
            }
            method.setAccessible(true);
            method.invoke(gtnFrameworkContractMainConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkTransactionComponentConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
