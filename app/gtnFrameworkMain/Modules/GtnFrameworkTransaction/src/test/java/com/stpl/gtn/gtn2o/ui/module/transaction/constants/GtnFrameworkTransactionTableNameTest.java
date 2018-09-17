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
public class GtnFrameworkTransactionTableNameTest {
    
    public GtnFrameworkTransactionTableNameTest() {
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
     * Test of values method, of class GtnFrameworkTransactionTableName.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        GtnFrameworkTransactionTableName[] result = GtnFrameworkTransactionTableName.values();
        assertFalse(result.length==0);
    }


    /**
     * Test of getTableName method, of class GtnFrameworkTransactionTableName.
     */
    @Test
    public void testGetTableName() {
        System.out.println("getTableName");
        GtnFrameworkTransactionTableName day = GtnFrameworkTransactionTableName.ACCRUAL_DETAILS;
        String result = day.getTableName();
        assertFalse(result.isEmpty());
    }
    
}
