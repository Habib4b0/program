/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.stpl.ifs.ui.util.NumericConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author shrihariharan
 */
public class ErrorCodeUtilTest {
    
   /**
     * List to execute negative test case
     */
    List<String> assertNegativeList;
    
    /**
     * List to execute negative test case
     */
    List<String> assertPositiveList;
    
    private String ERROR_CODE_1000="Technical Error, Please contact Administrator";
    private String ERROR_CODE_1001="Error";
    private String ERROR_CODE_1002="Populate Operation Failed";
    private String CODE_1017="1017 - Technical Error, Please contact Administrator";
    private String CODE_1018="1018 - Technical Error, Please contact Administrator";
    private String CODE_1019="1019 - Technical Error, Please contact Administrator";
    List<Exception> exception;
    
    
    /**
     * Initialize the lists before executing the Test Case
     */
    
    @Before
    public void setUp() {
        
        assertNegativeList=new ArrayList<>();
        assertNegativeList.add("");
        assertNegativeList.add(null);
        assertNegativeList.add("null");
        assertNegativeList.add("$@^!");
        
        assertPositiveList=new ArrayList<>();
        for(int msgNo=0;msgNo<=NumericConstants.TWO;msgNo++){
            assertPositiveList.add("ERROR_CODE_100"+msgNo);
        }
        
        exception=new ArrayList<>();
        exception.add(new IOException());
        exception.add(new RuntimeException());
    }
    
    /**
     * Test of getEC method, of class ErrorCodeUtil.
     */
    @Test
    public void testGetEC() {
        
        for(String key:assertNegativeList){
            Assert.assertEquals(assertNegativeList.get(0), ErrorCodeUtil.getEC(key));
        } 
      }

    /**
     * Positive Test Case for getErrorCode() method
     */
    @Test
    public void testPositiveGetEC(){
       Assert.assertEquals(ERROR_CODE_1000, ErrorCodeUtil.getEC(assertPositiveList.get(0)));
       Assert.assertEquals(ERROR_CODE_1001, ErrorCodeUtil.getEC(assertPositiveList.get(NumericConstants.ONE)));
       Assert.assertEquals(ERROR_CODE_1002, ErrorCodeUtil.getEC(assertPositiveList.get(NumericConstants.TWO)));
    }
    
    /**
     * Negative Test Case for getMessage() method
     */
    @Test(expected=NullPointerException.class)
    public void testNegativeErrorMessage(){
       Assert.assertEquals(ERROR_CODE_1000, ErrorCodeUtil.getErrorMessage(null));
    }
    
    /**
     * Positive Test Case for getMEssage() method
     */
    @Test
    public void testPositiveErrorMessage(){
        Assert.assertEquals(ERROR_CODE_1000, ErrorCodeUtil.getErrorMessage(exception.get(0)));
        Assert.assertEquals(ERROR_CODE_1000, ErrorCodeUtil.getErrorMessage(exception.get(1)));        
    }
    
    /**
     * After executing all test cases nullify the objects
     */
    @After
    public void tearDown(){
        assertNegativeList=null;
        assertPositiveList=null;
        exception=null;
    } 
   
}