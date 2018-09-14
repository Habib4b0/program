/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.constants;

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
public class GtnTransactionUIConstantsTest {
    
    public GtnTransactionUIConstantsTest() {
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
     * Test of getViewEnable method, of class GtnTransactionUIConstants.
     */
    @Test
    public void testGetViewEnable() {
        System.out.println("getViewEnable");
        String[] expResult = {"AccrualMaster", "ActualsMaster", "AuditMasterInbound",
			"CpiIndexMaster", "CustomerGtsActual", "ForecastingMaster", "GlBalanceMaster", "ReturnsMaster",
			"SalesMaster", "VwCompanyIdentifier", "VwCompanyMaster", "VwCompanyParentDetails", "VwCompanyTradeClass",
			"VwCustomerGtsForecast", "VwAdjustDemandForecastAct", "VwInventoryWdActualProjMas", "VwItemIdentifier",
			"VwItemMaster", "VwItemPricing", "IvldCustomerGtsForecast", "IvldCustomerGtsActual", "IvldForecastSales",
			"IvldSalesMaster", "IvldCpi", "IvldCompanyMaster", "IvldCompanyParent", "IvldCompanyTradeClass",
			"IvldItemMaster", "IvldItemIdentifier", "IvldItemPricing", "IvldGlBalance", "IvldCompanyIdentifier",
			"VwIvldInventoryWdActualProjMas", "IvldReturns", "IvldAccrualInbound", "IvldAccrualInbound", "IfpModel",
			"IvldIfp", "CfpModel", "IvldCfp", "VwContractHeader", "IvldContractHeader", "PsModel", "IvldPriceSchedule",
			"RebatePlanMaster", "IvldRebatePlan", "IvldRebateSchedule", "VwRebateSchedule", "ReturnRateForecast",
			"IvldReturnRateforecast" ,"ItemUom","IvldItemUom"};
        String[] result = GtnTransactionUIConstants.getViewEnable();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getDateFormat method, of class GtnTransactionUIConstants.
     */
    @Test
    public void testGetDateFormat() {
        System.out.println("getDateFormat");
        String[] expResult = { "dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss.S", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy", "MMM dd yyyy hh:mm", "dd/MM/yy", "dd/MM/yyyy",
			"d/M/yy", "d/M/yyyy", "ddMMyy", "ddMMyyyy", "ddMMMyy", "ddMMMyyyy", "dd-MMM-yyyy", "dMMMyy", "dMMMyyyy",
			"d-MMM-yy", "d-MMM-yyyy", "d-MMMM-yy", "d-MMMM-yyyy", "yyMMdd", "yyyyMMdd", "yy/MM/dd", "yyyy/MM/dd",
			"MMddyy", "MMddyyyy", "MM/dd/yy", "MM/dd/yyyy", "MMM-dd-yy", "MMM-dd-yyyy", "yyyy-MM-dd",
			"MMM dd yyyy hh:mm aaa"};
        String[] result = GtnTransactionUIConstants.getDateFormat();
        assertArrayEquals(expResult, result);
    }
    
}
