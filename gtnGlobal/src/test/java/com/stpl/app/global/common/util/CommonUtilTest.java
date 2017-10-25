/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.common.util;

import com.vaadin.ui.TextField;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author satheesh.n
 */
public class CommonUtilTest {
    
    private static final Logger LOGGER = Logger.getLogger(CommonUtilTest.class);
    
    public CommonUtilTest() {
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
     * Test of textValidation method, of class CommonUtil.
     */
    @Test
    public void testTextValidation() {
        LOGGER.debug("textValidation");
        TextField textField=new TextField();
        Object obj = textField;
        Object key = textField.getData();
        CommonUtil instance = CommonUtil.getInstance();
         instance.textValidation(obj, key);
    }


    /**
     * Test of getHeaderMessage method, of class CommonUtil.
     */
    @Test
    public void testGetHeaderMessage() {
        LOGGER.debug("getHeaderMessage");
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "Confirmation";
        String result = instance.getHeaderMessage();
        assertEquals(expResult, result);
       
    }
    
        

    /**
     * Test of getAccessDeniedHeaderMessage method, of class CommonUtil.
     */
    @Test
    public void testGetAccessDeniedHeaderMessage() {
        LOGGER.debug("getAccessDeniedHeaderMessage");
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "Access Denied";
        String result = instance.getAccessDeniedHeaderMessage();
        assertEquals(expResult, result);
        
    }
  

    /**
     * Test of getPermissionDeniedToDelete method, of class CommonUtil.
     */
    @Test
    public void testGetPermissionDeniedToDelete() {
        LOGGER.debug("getPermissionDeniedToDelete");
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "You are not having permission to Delete this record";
        String result = instance.getPermissionDeniedToDelete();
        assertEquals(expResult, result);
        
    }
   
    @Test
    public void testGetPermissionDeniedToEdit() {
        LOGGER.debug("getPermissionDeniedToEdit");
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "You are not having permission to edit this record";
        String result = instance.getPermissionDeniedToEdit();
        assertEquals(expResult, result);
       
    }

    @Test
    public void testGetSaveMessage() {
        LOGGER.debug("getSaveMessage");
        String recordName = "itemFamilyplanName";
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "Save record itemFamilyplanName?";
        String result = instance.getSaveMessage(recordName);
        assertEquals(expResult, result);
        
    }
       @Test
    public void negativeGetSaveMessage1() {
        LOGGER.debug("getnegativeSaveMessage1");
     
      CommonUtil instance = CommonUtil.getInstance();
      String recordName ="null";
        String result = instance.getSaveMessage(recordName);
        assertEquals("Save record "+recordName+"?", result);
        
        
        
  }
 @Test
    public void negativeGetSaveMessage2() {
        LOGGER.debug("getnegativeSaveMessage2");
        CommonUtil instance = CommonUtil.getInstance();
      String recordName =null;
       String result = instance.getSaveMessage(recordName);
        assertEquals("Save record null?", result);
        
        
        
  }
 @Test
    public void negativeGetSaveMessage3() {
        LOGGER.debug("getnegativeSaveMessage1");
     
      CommonUtil instance = CommonUtil.getInstance();
      String recordName ="";
        String result = instance.getSaveMessage(recordName);
        assertEquals("Save record "+recordName+"?", result);
         
        
        
  }

    /**
     * Test of getResetMessage method, of class CommonUtil.
     */
    @Test
    public void testGetResetMessage() {
        LOGGER.debug("getResetMessage");
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "Are you sure you want to reset the page to default/previous values?";
        String result = instance.getResetMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDeleteMessage method, of class CommonUtil.
     */
    @Test
    public void testGetDeleteMessage() {
        LOGGER.debug("getDeleteMessage");
       String recordName = com.stpl.app.global.cfp.util.FieldNameUtils.COMPANYFAMILYPLANNAME;

        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "Are you sure you want to delete record  companyFamilyPlanName?";
        String result = instance.getDeleteMessage(recordName);
        
        assertEquals(expResult, result);
    }
     @Test
    public void negativeGetDeleteMessage1() {
        LOGGER.debug("getnegativeDeleteMessage1");
     
      CommonUtil instance = CommonUtil.getInstance();
      String recordName ="null";
        String result = instance.getDeleteMessage(recordName);
        assertEquals("Are you sure you want to delete record  "+recordName+"?", result);
         
        
        
  }
 @Test
    public void negativeGetDeleteMessage2() {
        LOGGER.debug("getnegativeSaveMessage2");
        CommonUtil instance = CommonUtil.getInstance();
      String recordName =null;
       String result = instance.getDeleteMessage(recordName);
        assertEquals("Are you sure you want to delete record  null?", result);
         
        
        
  }
 @Test
    public void negativeGetDeleteMessage3() {
        LOGGER.debug("getnegativeSaveMessage3");
     
      CommonUtil instance = CommonUtil.getInstance();
      String recordName ="";
        String result = instance.getDeleteMessage(recordName);
        assertEquals("Are you sure you want to delete record  "+recordName+"?", result);
        
        
        
  }
    /**
     * Test of getBackMessage method, of class CommonUtil.
     */
    @Test
    public void testGetBackMessage() {
        LOGGER.debug("getBackMessage");
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "Any unsaved information will not be saved. Do you want to proceed?";
        String result = instance.getBackMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSavedSuccessfulMessage method, of class CommonUtil.
     */
    @Test
    public void testGetSavedSuccessfulMessage() {
        LOGGER.debug("getSavedSuccessfulMessage");
        String recordId = com.stpl.app.util.ConstantsUtils.COMPANY_ID;
        String recordName = com.stpl.app.util.ConstantsUtils.COMPANY_NAME;
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "companyId,companyName has been successfully saved";
        String result = instance.getSavedSuccessfulMessage(recordId, recordName);
        assertEquals(expResult, result);
    }
 @Test
    public void negativeSavedSuccessfulMessage1() {
        LOGGER.debug("getnegativeSavedSuccessfulMessage1");
     
      CommonUtil instance = CommonUtil.getInstance();
      String recordId = null;
        String recordName = null;
        String result = instance.getSavedSuccessfulMessage(recordId, recordName);
        assertEquals(recordId+","+recordName+" has been successfully saved", result);
        
        
        
  }
 @Test
    public void negativeSavedSuccessfulMessage2() {
         CommonUtil instance = CommonUtil.getInstance();
      String recordId = "null";
        String recordName = "null";
        String result = instance.getSavedSuccessfulMessage(recordId, recordName);
        assertEquals(recordId+","+recordName+" has been successfully saved", result);
        
        
        
  }
 @Test
    public void negativeSavedSuccessfulMessage3() {
        CommonUtil instance = CommonUtil.getInstance();
      String recordId = "";
        String recordName = "";
        String result = instance.getSavedSuccessfulMessage(recordId, recordName);
        assertEquals(recordId+","+recordName+" has been successfully saved", result);
        
        
        
  }
    /**
     * Test of getDeletedSuccessfulMessage method, of class CommonUtil.
     */
    @Test
    public void testGetDeletedSuccessfulMessage() {
        LOGGER.debug("getDeletedSuccessfulMessage");
        String recordId = com.stpl.app.util.ConstantsUtils.COMPANY_ID;
        String recordName = com.stpl.app.util.ConstantsUtils.COMPANY_NAME;
        CommonUtil instance = CommonUtil.getInstance();
        String expResult = "companyId,companyName has been successfully deleted";
        String result = instance.getDeletedSuccessfulMessage(recordId, recordName);
        
        assertEquals(expResult, result);
    }
     @Test
    public void negativeDeletedSuccessfulMessage1() {
        LOGGER.debug("getnegativeSavedSuccessfulMessage1");
     
      CommonUtil instance = CommonUtil.getInstance();
      String recordId = null;
        String recordName = null;
        String result = instance.getDeletedSuccessfulMessage(recordId, recordName);
        assertEquals(recordId+","+recordName+" has been successfully deleted", result);
        
        
        
  }
 @Test
    public void negativeDeletedSuccessfulMessage2() {
         CommonUtil instance = CommonUtil.getInstance();
      String recordId = "null";
        String recordName = "null";
        String result = instance.getDeletedSuccessfulMessage(recordId, recordName);
        assertEquals(recordId+","+recordName+" has been successfully deleted", result);
         LOGGER.debug("result"+result);
        
        
  }
 @Test
    public void negativeDeletedSuccessfulMessage3() {
        CommonUtil instance = CommonUtil.getInstance();
      String recordId = "";
        String recordName = "";
        String result = instance.getDeletedSuccessfulMessage(recordId, recordName);
        assertEquals(recordId+","+recordName+" has been successfully deleted", result);
         
        
        
  }
}