/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.abstractsearch.util;



import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;




/**
 *
 * @author satheesh.n
 */
public class ValidationUtilTest {
    
     private static final Logger LOGGER = Logger.getLogger(ValidationUtilTest.class);

     /**
     * List to execute positive test case
     */
   

    /**
     * List to execute negative test case
     */
   

    public ValidationUtilTest() {
        
    }
    

    
    @Before
    public void setUp() {
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMC method, of class ValidationUtil.
     */
    @Test
    public void positivetestGetMC() {
        LOGGER.debug("getMC");
        String key="maxlengthvalidationitemnamehun";
        String result = ValidationUtil.getMC(key);
        String expResult = "Item Name Should be less than 100";
        
        assertEquals(expResult, result);
        LOGGER.debug("result");

    }
 @Test
    public void negativeGetMC() {
        LOGGER.debug("getnegativeMC1");
      String[] assertNegative = { "","null" };
         for(int i=0;i<assertNegative.length;i++)
         {
        String key=assertNegative[i];
        String result = ValidationUtil.getMC(key);
        assertEquals("", result);
         LOGGER.debug("result"+result);
        }
        
  }
// @Test(expected=NullPointerException.class)
//    public void negativeGetMC2() {
//        LOGGER.debug("getnegativeMC2");
//      String key =null;
//      String expectedResult="Item Name Should be less than 100";
//        String result = ValidationUtil.getMC(key);
//        assertEquals(expectedResult, result);
//         LOGGER.debug("result"+result);
//        
//        
//  }
    

    /**
     * Test of getLC method, of class ValidationUtil.
     */
    @Test
    public void testGetLC() {
        LOGGER.debug("getLC");
        String key = "label1_companymaster";
        String expResult = "Company ID";
        String result = ValidationUtil.getLC(key);
        assertEquals(expResult, result);
        LOGGER.debug("result is "+result);
       
    }
    @Test
    public void negativeGetLC1() {
        LOGGER.debug("getnegativeLC1");
      String[] assertNegative = { "","null" };
         for(int i=0;i<assertNegative.length;i++)
         {
        String key=assertNegative[i];
        String result = ValidationUtil.getLC(key);
        assertEquals("", result);
         LOGGER.debug("result"+result);
        }
        
  }
// @Test(expected=NullPointerException.class)
//    public void negativeGetLC2() {
//        LOGGER.debug("getnegativeLC2");
//      String key =null;
//      String expectedResult="Item Name Should be less than 100";
//        String result = ValidationUtil.getLC(key);
//        assertEquals(expectedResult, result);
//         LOGGER.debug("result"+result);
//        
//        
//  }

}