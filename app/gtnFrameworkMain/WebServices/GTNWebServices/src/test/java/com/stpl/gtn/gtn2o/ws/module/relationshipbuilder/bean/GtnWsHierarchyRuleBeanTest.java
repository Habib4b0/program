/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.bean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWsHierarchyRuleBeanTest {
    
    public GtnWsHierarchyRuleBeanTest() {
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
     * Test of getRuleName method, of class GtnWsHierarchyRuleBean.
     */
    @Test
    public void testGetRuleName() {
        System.out.println("getRuleName");
        GtnWsHierarchyRuleBean instance = new GtnWsHierarchyRuleBean();
        String expResult = "value";
        instance.setRuleName(expResult);
        String result = instance.getRuleName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDimension method, of class GtnWsHierarchyRuleBean.
     */
    @Test
    public void testGetDimension() {
        System.out.println("getDimension");
        GtnWsHierarchyRuleBean instance = new GtnWsHierarchyRuleBean();
       
        String expResult = "1";
        instance.setDimension(expResult);
        String result = instance.getDimension();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAttribute method, of class GtnWsHierarchyRuleBean.
     */
    @Test
    public void testGetAttribute() {
        System.out.println("getAttribute");
        GtnWsHierarchyRuleBean instance = new GtnWsHierarchyRuleBean();
        String expResult = "Attribute";
        instance.setAttribute(expResult);
        String result = instance.getAttribute();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCondition1 method, of class GtnWsHierarchyRuleBean.
     */
    @Test
    public void testGetCondition1() {
        System.out.println("getCondition1");
        GtnWsHierarchyRuleBean instance = new GtnWsHierarchyRuleBean();
        String expResult = "condition1";
        instance.setCondition1(expResult);
        String result = instance.getCondition1();
        assertEquals(expResult, result);
    }


    /**
     * Test of getValue1 method, of class GtnWsHierarchyRuleBean.
     */
    @Test
    public void testGetValue1() {
        System.out.println("getValue1");
        GtnWsHierarchyRuleBean instance = new GtnWsHierarchyRuleBean();
        String expResult = "value1";
           instance.setValue1(expResult);
        String result = instance.getValue1();
        assertEquals(expResult, result);
    }

    
}
