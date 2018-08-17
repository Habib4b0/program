/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
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
public class GtnFrameworkContractDashboardPrcingTabConfigTest {
    
    public GtnFrameworkContractDashboardPrcingTabConfigTest() {
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
     * Test of getTabConfig method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertFalse(result.getTabCaption().isEmpty());
    }

    /**
     * Test of addContractHeaderDetailDecissionLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddContractHeaderDetailDecissionLayout() {
        String methodName = "addContractHeaderDetailDecissionLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderFieldPanel method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddContractHeaderFieldPanel() {
        String methodName = "addContractHeaderFieldPanel";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionLevel method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddDecissionLevel() {
        String methodName = "addDecissionLevel";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionView method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddDecissionView() {
        String methodName = "addDecissionView";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHeaderFieldComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddHeaderFieldComponent() {
        String methodName = "addHeaderFieldComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleId method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleId() {
        String methodName = "addPriceScheduleId";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleNo method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleNo() {
        String methodName = "addPriceScheduleNo";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleName method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleName() {
        String methodName = "addPriceScheduleName";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleStatus method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleStatus() {
        String methodName = "addPriceScheduleStatus";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleStartDate method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleStartDate() {
        String methodName = "addPriceScheduleStartDate";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleEndDate method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleEndDate() {
        String methodName = "addPriceScheduleEndDate";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleDesignation method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleDesignation() {
        String methodName = "addPriceScheduleDesignation";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentPsId method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddParentPsId() {
        String methodName = "addParentPsId";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentPsName method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddParentPsName() {
        String methodName = "addParentPsName";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleType method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleType() {
        String methodName = "addPriceScheduleType";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCreatedBy method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddCreatedBy() {
        String methodName = "addCreatedBy";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCreatedDate method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddCreatedDate() {
        String methodName = "addCreatedDate";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPriceScheduleCategory method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPriceScheduleCategory() {
        String methodName = "addPriceScheduleCategory";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addModifiedBy method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddModifiedBy() {
        String methodName = "addModifiedBy";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addModifiedDate method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddModifiedDate() {
        String methodName = "addModifiedDate";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPsTradeClass method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPsTradeClass() {
        String methodName = "addPsTradeClass";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of itemsTabHistoryDataTableComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testItemsTabHistoryDataTableComponent() {
        String methodName = "itemsTabHistoryDataTableComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeatilsLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddDeatilsLayout() {
        String methodName = "addDeatilsLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of massUpdatePanel method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testMassUpdatePanel() {
        String methodName = "massUpdatePanel";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHiddenField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddHiddenField() {
        String methodName = "addHiddenField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMassCheck method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddMassCheck() {
    try {
            System.out.println("addMassCheck");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String cdPricingTabPrefix="";
            String parent="";
            GtnUIFrameWorkActionConfig pricingMassCheckEnableDisableAction=new GtnUIFrameWorkActionConfig();
            GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addMassCheck",List.class,String.class,String.class,GtnUIFrameWorkActionConfig.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,cdPricingTabPrefix,parent,pricingMassCheckEnableDisableAction);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDashboardPrcingTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addMassPopulateField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddMassPopulateField() {
        String methodName = "addMassPopulateField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPopulateButtonLayout() {
        String methodName = "addPopulateButtonLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPopulateButtonComponent() {
        String methodName = "addPopulateButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of recordLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testRecordLayout() {
        String methodName = "recordLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of psResultPanel method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testPsResultPanel() {
        String methodName = "psResultPanel";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of psViewResultTable method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testPsViewResultTable() {
        String methodName = "psViewResultTable";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButtonLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddExcelButtonLayout() {
        String methodName = "addExcelButtonLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
        String methodName = "addExcelButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addViewExcelButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddViewExcelButtonComponent() {
        String methodName = "addViewExcelButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getTableEditableField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testGetTableEditableField() {
        String methodName = "getTableEditableField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingLayout() {
        String methodName = "addPricingLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of pricingMassUpdatePanel method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testPricingMassUpdatePanel() {
        String methodName = "pricingMassUpdatePanel";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingHiddenField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingHiddenField() {
        String methodName = "addPricingHiddenField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingMassCheck method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingMassCheck() {
    try {
            System.out.println("addPricingMassCheck");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String cdPricingTabPrefix="";
            String parent="";
            GtnUIFrameWorkActionConfig pricingMassCheckEnableDisableAction=new GtnUIFrameWorkActionConfig();
            GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addPricingMassCheck",List.class,String.class,String.class,GtnUIFrameWorkActionConfig.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,cdPricingTabPrefix,parent,pricingMassCheckEnableDisableAction);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDashboardPrcingTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPricingMassPopulateField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingMassPopulateField() {
        String methodName = "addPricingMassPopulateField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddField() {
        String methodName = "addField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addComboField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddComboField() {
        String methodName = "addComboField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addLookUpField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddLookUpField() {
        String methodName = "addLookUpField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldFactoryField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddFieldFactoryField() {
        String methodName = "addFieldFactoryField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldFactoryComboField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddFieldFactoryComboField() {
        String methodName = "addFieldFactoryComboField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldFactoryLookUpField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddFieldFactoryLookUpField() {
        String methodName = "addFieldFactoryLookUpField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingPopulateButtonLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingPopulateButtonLayout() {
        String methodName = "addPricingPopulateButtonLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingPopulateButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingPopulateButtonComponent() {
        String methodName = "addPricingPopulateButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of pricingRecordLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testPricingRecordLayout() {
        String methodName = "pricingRecordLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of pricingResultPanel method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testPricingResultPanel() {
        String methodName = "pricingResultPanel";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of pricingViewResultTable method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testPricingViewResultTable() {
        String methodName = "pricingViewResultTable";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingExcelButtonLayout method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingExcelButtonLayout() {
        String methodName = "addPricingExcelButtonLayout";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingExcelButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingExcelButtonComponent() {
        String methodName = "addPricingExcelButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPricingViewExcelButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddPricingViewExcelButtonComponent() {
        String methodName = "addPricingViewExcelButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getPricingTableEditableField method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testGetPricingTableEditableField() {
        String methodName = "getPricingTableEditableField";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getDetailsTableFilterFieldMap method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testGetDetailsTableFilterFieldMap() {
        String methodName = "getDetailsTableFilterFieldMap";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getPricingTableFilterFieldMap method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testGetPricingTableFilterFieldMap() {
        String methodName = "getPricingTableFilterFieldMap";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addAddLineButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddAddLineButtonComponent() {
        String methodName = "addAddLineButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRemoveLineButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddRemoveLineButtonComponent() {
        String methodName = "addRemoveLineButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCopyLineButtonComponent method, of class GtnFrameworkContractDashboardPrcingTabConfig.
     */
    @Test
    public void testAddCopyLineButtonComponent() {
        String methodName = "addCopyLineButtonComponent";
        GtnFrameworkContractDashboardPrcingTabConfig instance = new GtnFrameworkContractDashboardPrcingTabConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardPrcingTabConfig gtnFrameworkContractDashboardInfomationTabConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardInfomationTabConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardInfomationTabConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardPrcingTabConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            method.invoke(gtnFrameworkContractDashboardInfomationTabConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardPrcingTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
