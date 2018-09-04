/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.constants;

import static com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_CONTRACT_START_DATE;
import static com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_STATUS;
import static com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_TYPE;
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
public class GtnUIFrameworkContractHeaderStringContantsTest {
    
    public GtnUIFrameworkContractHeaderStringContantsTest() {
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
     * Test of getCustomListNameArray method, of class GtnUIFrameworkContractHeaderStringContants.
     */
    @Test
    public void testGetCustomListNameArray() {
        System.out.println("getCustomListNameArray");
        String[] expResult = {"STATUS", "CONTRACT_TYPE", "CONTRACT_TRADE_CLASS"};
        String[] result = GtnUIFrameworkContractHeaderStringContants.getCustomListNameArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getCustomPropertyFields method, of class GtnUIFrameworkContractHeaderStringContants.
     */
    @Test
    public void testGetCustomPropertyFields() {
        System.out.println("getCustomPropertyFields");
        String[] expResult = {"contractStatus", "contractType", "tradeClass"};
        String[] result = GtnUIFrameworkContractHeaderStringContants.getCustomPropertyFields();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getDoActionFields method, of class GtnUIFrameworkContractHeaderStringContants.
     */
    @Test
    public void testGetDoActionFields() {
        System.out.println("getDoActionFields");
        String[] expResult = {GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_ID,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_CONTRACT_NO,
				"contractHeaderTabContractName", CONTRACT_HEADER_TAB_CONTRACT_STATUS, CONTRACT_HEADER_TAB_CONTRACT_TYPE, CONTRACT_HEADER_CONTRACT_START_DATE,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER};
        String[] result = GtnUIFrameworkContractHeaderStringContants.getDoActionFields();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getDoActionMesaages method, of class GtnUIFrameworkContractHeaderStringContants.
     */
    @Test
    public void testGetDoActionMesaages() {
        System.out.println("getDoActionMesaages");
        String[] expResult = {GtnUIFrameworkContractHeaderStringContants.GTN_CUSTOMER_GRP_VALIDATION_MSG_ALIAS_NO_MANDATORY,
				GtnUIFrameworkContractHeaderStringContants.GTN_CUSTOMER_GRP_VALIDATION_MSG_ALIAS_NAME_MANDATORY,
				GtnUIFrameworkContractHeaderStringContants.GTN_CUSTOMER_GRP_VALIDATION_MSG_ALIAS_START_MANDATORY};
        String[] result = GtnUIFrameworkContractHeaderStringContants.getDoActionMesaages();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getEditViewButtons method, of class GtnUIFrameworkContractHeaderStringContants.
     */
    @Test
    public void testGetEditViewButtons() {
        System.out.println("getEditViewButtons");
        String[] expResult = {"contractAliasInformationPanel", "identifierAttachButton",
				"identifierRemoveButton", "contractHeaderAddResetButton"};
        String[] result = GtnUIFrameworkContractHeaderStringContants.getEditViewButtons();
        assertArrayEquals(expResult, result);
    }
    
}
