/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardMainConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardMainConfigTest;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import java.lang.reflect.InvocationTargetException;
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
public class GtnFrameworkContractDashboardCompaniesTabConfigTest {

    public GtnFrameworkContractDashboardCompaniesTabConfigTest() {
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
     * Test of getTabConfig method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardCompaniesTabConfig instance = new GtnFrameworkContractDashboardCompaniesTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertEquals(result.getTabCaption(), "Companies");
    }

    /**
     * Test of addContractHeaderDetailDecissionLayout method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddContractHeaderDetailDecissionLayout() {
        String methodName = "addContractHeaderDetailDecissionLayout";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderFieldPanel method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddContractHeaderFieldPanel() {
        String methodName = "addContractHeaderFieldPanel";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecisionLevel method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddDecisionLevel() {
        String methodName = "addDecisionLevel";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionView method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddDecissionView() {
        String methodName = "addDecissionView";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHeaderFieldComponent method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddHeaderFieldComponent() {
        String methodName = "addHeaderFieldComponent";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpId method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpId() {
        String methodName = "addCfpId";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpNo method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpNo() {
        String methodName = "addCfpNo";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpName method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpName() {
        String methodName = "addCfpName";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpStatus method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpStatus() {
        String methodName = "addCfpStatus";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpStartDate method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpStartDate() {
        String methodName = "addCfpStartDate";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpEndDate method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpEndDate() {
        String methodName = "addCfpEndDate";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpType method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpType() {
        String methodName = "addCfpType";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpCategory method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpCategory() {
        String methodName = "addCfpCategory";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpTradeClass method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpTradeClass() {
        String methodName = "addCfpTradeClass";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpDesignation method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCfpDesignation() {
        String methodName = "addCfpDesignation";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentCfpId method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddParentCfpId() {
        String methodName = "addParentCfpId";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentCfpName method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddParentCfpName() {
        String methodName = "addParentCfpName";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSalesInclusion method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddSalesInclusion() {
        String methodName = "addSalesInclusion";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCreatedBy method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCreatedBy() {
        String methodName = "addCreatedBy";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCreatedDate method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddCreatedDate() {
        String methodName = "addCreatedDate";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addModifiedBy method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddModifiedBy() {
        String methodName = "addModifiedBy";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addModifiedDate method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddModifiedDate() {
        String methodName = "addModifiedDate";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of companiesTabHistoryDataTableComponent method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testCompaniesTabHistoryDataTableComponent() {
        String methodName = "companiesTabHistoryDataTableComponent";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeatilsLayout method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddDeatilsLayout() {
        String methodName = "addDeatilsLayout";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of massUpdatePanel method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testMassUpdatePanel() {
        String methodName = "massUpdatePanel";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHiddenField method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddHiddenField() {
        String methodName = "addHiddenField";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMassCheck method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddMassCheck() {
        String methodName = "addMassCheck";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMassPopulateField method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddMassPopulateField() {
        String methodName = "addMassPopulateField";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonLayout method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddPopulateButtonLayout() {
        String methodName = "addPopulateButtonLayout";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonComponent method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddPopulateButtonComponent() {
        String methodName = "addPopulateButtonComponent";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of reordLayout method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testReordLayout() {
        String methodName = "reordLayout";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of companiesResultPanel method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testCompaniesResultPanel() {
        String methodName = "companiesResultPanel";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of companiesViewResultTable method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testCompaniesViewResultTable() {
        String methodName = "companiesViewResultTable";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRemoveButtonLayout method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddRemoveButtonLayout() {
        String methodName = "addRemoveButtonLayout";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButtonComponent method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
        String methodName = "addExcelButtonComponent";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addViewExcelButtonComponent method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testAddViewExcelButtonComponent() {
        String methodName = "addViewExcelButtonComponent";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getTableEditableField method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testGetTableEditableField() {
        String methodName = "getTableEditableField";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getTableFilterFieldMap method, of class
     * GtnFrameworkContractDashboardCompaniesTabConfig.
     */
    @Test
    public void testGetTableFilterFieldMap() {
        try {
            System.out.println("getTableFilterFieldMap");
            GtnFrameworkContractDashboardCompaniesTabConfig instance = new GtnFrameworkContractDashboardCompaniesTabConfig();
            Method method = instance.getClass().getDeclaredMethod("getTableFilterFieldMap");
            method.setAccessible(true);
            Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> map = (Map<String, GtnUIFrameworkPagedTableCustomFilterConfig>) method.invoke(instance);
            assertFalse(map.isEmpty());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardCompaniesTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardMainConfig gtnFrameworkContractMainConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractMainConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractMainConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardMainConfig gtnFrameworkContractMainConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
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
            Logger.getLogger(GtnFrameworkContractDashboardMainConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }

}
