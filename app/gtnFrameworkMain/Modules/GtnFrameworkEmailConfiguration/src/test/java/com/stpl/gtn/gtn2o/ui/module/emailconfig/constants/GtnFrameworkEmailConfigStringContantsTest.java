/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.constants;

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
public class GtnFrameworkEmailConfigStringContantsTest {
    
    public GtnFrameworkEmailConfigStringContantsTest() {
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
     * Test of getSmtpComboBoxDataLoad method, of class GtnFrameworkEmailConfigStringContants.
     */
    @Test
    public void testGetSmtpComboBoxDataLoad() {
        System.out.println("getSmtpComboBoxDataLoad");
        String[] expResult = {"Yes", "No"};
        String[] result = GtnFrameworkEmailConfigStringContants.getSmtpComboBoxDataLoad();
        assertArrayEquals(expResult, result);
    }
    
}
