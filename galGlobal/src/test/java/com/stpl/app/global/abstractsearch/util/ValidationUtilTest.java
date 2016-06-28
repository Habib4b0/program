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
        LOGGER.info("getMC");
        String key="maxlengthvalidationitemnamehun";
        String result = ValidationUtil.getMC(key);
        String expResult = "Item Name Should be less than 100";
        
        assertEquals(expResult, result);
        LOGGER.info("result");

    }
 @Test
    public void negativeGetMC() {
        LOGGER.info("getnegativeMC1");
      String[] assertNegative = { "","null" };
         for(int i=0;i<assertNegative.length;i++)
         {
        String key=assertNegative[i];
        String result = ValidationUtil.getMC(key);
        assertEquals("", result);
         LOGGER.info("result"+result);
        }
        
  }
 @Test(expected=NullPointerException.class)
    public void negativeGetMC2() {
        LOGGER.info("getnegativeMC2");
      String key =null;
      String expectedResult="Item Name Should be less than 100";
        String result = ValidationUtil.getMC(key);
        assertEquals(expectedResult, result);
         LOGGER.info("result"+result);
        
        
  }
    

    /**
     * Test of getLC method, of class ValidationUtil.
     */
    @Test
    public void testGetLC() {
        LOGGER.info("getLC");
        String key = "label1_companymaster";
        String expResult = "Company ID";
        String result = ValidationUtil.getLC(key);
        assertEquals(expResult, result);
        LOGGER.info("result is "+result);
       
    }
    @Test
    public void negativeGetLC1() {
        LOGGER.info("getnegativeLC1");
      String[] assertNegative = { "","null" };
         for(int i=0;i<assertNegative.length;i++)
         {
        String key=assertNegative[i];
        String result = ValidationUtil.getLC(key);
        assertEquals("", result);
         LOGGER.info("result"+result);
        }
        
  }
 @Test(expected=NullPointerException.class)
    public void negativeGetLC2() {
        LOGGER.info("getnegativeLC2");
      String key =null;
      String expectedResult="Item Name Should be less than 100";
        String result = ValidationUtil.getLC(key);
        assertEquals(expectedResult, result);
         LOGGER.info("result"+result);
        
        
  }

}