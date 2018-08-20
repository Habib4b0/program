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
public class GtnFrameworkContractDashboardRebateTabConfigTest {
    
    public GtnFrameworkContractDashboardRebateTabConfigTest() {
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
     * Test of getTabConfig method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertFalse(result.getTabCaption().isEmpty());
    }

    /**
     * Test of addContractHeaderDetailDecissionLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddContractHeaderDetailDecissionLayout() {
        String methodName = "addContractHeaderDetailDecissionLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderFieldPanel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddContractHeaderFieldPanel() {
        String methodName = "addContractHeaderFieldPanel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionLevel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddDecissionLevel() {
        String methodName = "addDecissionLevel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDecissionView method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddDecissionView() {
        String methodName = "addDecissionView";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHeaderFieldRSIComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddHeaderFieldRSIComponent() {
        String methodName = "addHeaderFieldRSIComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHeaderFieldRPOComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddHeaderFieldRPOComponent() {
        String methodName = "addHeaderFieldRPOComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleId method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleId() {
        String methodName = "addRebateScheduleId";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleNo method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleNo() {
        String methodName = "addRebateScheduleNo";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleName method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleName() {
        String methodName = "addRebateScheduleName";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleStatus method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleStatus() {
        String methodName = "addRebateScheduleStatus";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleType method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleType() {
        String methodName = "addRebateScheduleType";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateProgramType method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateProgramType() {
        String methodName = "addRebateProgramType";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleCategory method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleCategory() {
        String methodName = "addRebateScheduleCategory";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleStartDate method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleStartDate() {
        String methodName = "addRebateScheduleStartDate";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleEndDate method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleEndDate() {
        String methodName = "addRebateScheduleEndDate";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRsTradeClass method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRsTradeClass() {
        String methodName = "addRsTradeClass";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleDesignation method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleDesignation() {
        String methodName = "addRebateScheduleDesignation";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateScheduleAliasID method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateScheduleAliasID() {
        String methodName = "addRebateScheduleAliasID";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentRsId method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddParentRsId() {
        String methodName = "addParentRsId";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addParentRsName method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddParentRsName() {
        String methodName = "addParentRsName";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRSTransactionRefId method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRSTransactionRefId() {
        String methodName = "addRSTransactionRefId";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRSTransactionRefName method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRSTransactionRefName() {
        String methodName = "addRSTransactionRefName";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeductionInclusion method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddDeductionInclusion() {
        String methodName = "addDeductionInclusion";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUdc1 method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddUdc1() {
        String methodName = "addUdc1";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUdc2 method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddUdc2() {
        String methodName = "addUdc2";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUdc3 method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddUdc3() {
        String methodName = "addUdc3";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUdc4 method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddUdc4() {
        String methodName = "addUdc4";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUdc5 method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddUdc5() {
        String methodName = "addUdc5";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUdc6 method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddUdc6() {
        String methodName = "addUdc6";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCalendar method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddCalendar() {
        String methodName = "addCalendar";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateFrequency method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateFrequency() {
        String methodName = "addRebateFrequency";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPaymentLevel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPaymentLevel() {
        String methodName = "addPaymentLevel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPaymentFrequency method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPaymentFrequency() {
        String methodName = "addPaymentFrequency";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPaymentGracePeriod method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPaymentGracePeriod() {
        String methodName = "addPaymentGracePeriod";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPaymentTerms method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPaymentTerms() {
        String methodName = "addPaymentTerms";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPaymentMethod method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPaymentMethod() {
        String methodName = "addPaymentMethod";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInterestBearingBasis method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddInterestBearingBasis() {
        String methodName = "addInterestBearingBasis";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEvaluationRuleLevel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddEvaluationRuleLevel() {
        String methodName = "addEvaluationRuleLevel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEvaluationRuleType method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddEvaluationRuleType() {
        String methodName = "addEvaluationRuleType";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEvaluationRule method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddEvaluationRule() {
        String methodName = "addEvaluationRule";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInterestBearingIndicator method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddInterestBearingIndicator() {
        String methodName = "addInterestBearingIndicator";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCalculationRuleLevel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddCalculationRuleLevel() {
        String methodName = "addCalculationRuleLevel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCalculationType method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddCalculationType() {
        String methodName = "addCalculationType";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCalculationRule method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddCalculationRule() {
        String methodName = "addCalculationRule";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCalculationLevel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddCalculationLevel() {
        String methodName = "addCalculationLevel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of itemsTabHistoryDataTableComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testItemsTabHistoryDataTableComponent() {
        String methodName = "itemsTabHistoryDataTableComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeatilsLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddDeatilsLayout() {
        String methodName = "addDeatilsLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRemoveButtonLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRemoveButtonLayout() {
        String methodName = "addRemoveButtonLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
        String methodName = "addExcelButtonComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addViewExcelButtonComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddViewExcelButtonComponent() {
        String methodName = "addViewExcelButtonComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of massUpdatePanel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testMassUpdatePanel() {
        String methodName = "massUpdatePanel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHiddenField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddHiddenField() {
        String methodName = "addHiddenField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMassCheck method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddMassCheck() {
    try {
            System.out.println("addMassCheck");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String cdPricingTabPrefix="";
            String parent="";
            GtnUIFrameWorkActionConfig pricingMassCheckEnableDisableAction=new GtnUIFrameWorkActionConfig();
            GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
            Method method = instance.getClass().getDeclaredMethod("addMassCheck",List.class,String.class,String.class,GtnUIFrameWorkActionConfig.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,cdPricingTabPrefix,parent,pricingMassCheckEnableDisableAction);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDashboardPrcingTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addMassPopulateField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddMassPopulateField() {
        String methodName = "addMassPopulateField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPopulateButtonLayout() {
        String methodName = "addPopulateButtonLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPopulateButtonComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddPopulateButtonComponent() {
        String methodName = "addPopulateButtonComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of recordLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testRecordLayout() {
        String methodName = "recordLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of rebateResultPanel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testRebateResultPanel() {
        String methodName = "rebateResultPanel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of rebateViewResultTable method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testRebateViewResultTable() {
        String methodName = "rebateViewResultTable";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getTableEditableField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testGetTableEditableField() {
        String methodName = "getTableEditableField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessingLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddProcessingLayout() {
        String methodName = "addProcessingLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessingFieldComponent method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddProcessingFieldComponent() {
        String methodName = "addProcessingFieldComponent";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRebateProcessingType method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddRebateProcessingType() {
        String methodName = "addRebateProcessingType";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addValidationProfile method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddValidationProfile() {
        String methodName = "addValidationProfile";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessInterestBearingIndicator method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddProcessInterestBearingIndicator() {
        String methodName = "addProcessInterestBearingIndicator";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessInterestBearingBasis method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddProcessInterestBearingBasis() {
        String methodName = "addProcessInterestBearingBasis";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessingDualListBoxPanel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddProcessingDualListBoxPanel() {
        String methodName = "addProcessingDualListBoxPanel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDualListBoxLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddDualListBoxLayout() {
        String methodName = "addDualListBoxLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveButtonsLayout method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testMoveButtonsLayout() {
        String methodName = "moveButtonsLayout";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveRight method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testMoveRight() {
        String methodName = "moveRight";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveLeft method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testMoveLeft() {
        String methodName = "moveLeft";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveAllRight method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testMoveAllRight() {
        String methodName = "moveAllRight";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveAllLeft method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testMoveAllLeft() {
        String methodName = "moveAllLeft";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of leftResultDataTable method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testLeftResultDataTable() {
        String methodName = "leftResultDataTable";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of rightResultDataTable method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testRightResultDataTable() {
        String methodName = "rightResultDataTable";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddField() {
        String methodName = "addField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addComboField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddComboField() {
        String methodName = "addComboField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addLookUpField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddLookUpField() {
        String methodName = "addLookUpField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getPopupActionConfig method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testGetPopupActionConfig() {
    try {
            System.out.println("getPopupActionConfig");
            GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
            String componentId="";
            String lookUpView="";
            String lookUpName="";
	    String lookUpColumn="";
            String lookUpfieldValue="";
            GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
            Method method = instance.getClass().getDeclaredMethod("getPopupActionConfig",String.class,String.class,String.class,String.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentId,lookUpView,lookUpName,lookUpColumn,lookUpfieldValue);
            assertFalse( popupActionConfig.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDashboardRebateTabConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldFactoryField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddFieldFactoryField() {
        String methodName = "addFieldFactoryField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldFactoryComboField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddFieldFactoryComboField() {
        String methodName = "addFieldFactoryComboField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldFactoryLookUpField method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testAddFieldFactoryLookUpField() {
        String methodName = "addFieldFactoryLookUpField";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of rebateHistoryResultPanel method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testRebateHistoryResultPanel() {
        String methodName = "rebateHistoryResultPanel";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getRebateTableFilterFieldMap method, of class GtnFrameworkContractDashboardRebateTabConfig.
     */
    @Test
    public void testGetRebateTableFilterFieldMap() {
        String methodName = "getRebateTableFilterFieldMap";
        GtnFrameworkContractDashboardRebateTabConfig instance = new GtnFrameworkContractDashboardRebateTabConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardRebateTabConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardRebateTabConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
