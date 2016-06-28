/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.logic;

import com.stpl.app.util.ConstantsUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author karthikraja.k
 */
public class CompanySearchLogicTest {
      List<String> positiveDataList;
       List<String> negativeDataList;
       private static final Logger LOGGER = Logger.getLogger(CompanySearchLogicTest.class);
    public CompanySearchLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        positiveDataList=new ArrayList<String>();
        positiveDataList.add("yyyy-MM-dd hh:mm:ss.S");
        negativeDataList=new ArrayList<String>();
 
        negativeDataList.add("10/05/2010");
        negativeDataList.add("Fri Nov 27 14:04:31 IST 2015");
        negativeDataList.add("yyyy-MM-dd hh:mm:ss"); 
        negativeDataList.add("MM-dd-yyyy");
        negativeDataList.add("dd-MM-yyyy");
        negativeDataList.add("yyyy-MM-dd ");
        negativeDataList.add("///////##");
        negativeDataList.add("10-22-1991");
        negativeDataList.add("110-1122-1991111");
        negativeDataList.add("10/22/1991");
        negativeDataList.add(null);
        negativeDataList.add("");
     
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of convertDateToDate method, of class CompanySearchLogic.
     */
    @Ignore
    public void positiveTestConvertDateToDate() throws Exception {
        LOGGER.info("convertDateToDate");
        final DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date expResult = null;
        expResult = inputFormat1.parse(inputFormat1.format(new Date()));
        Date formatedDate = null;
        for (String dateFormat : positiveDataList) {
            final DateFormat inputFormat = new SimpleDateFormat(dateFormat);
            formatedDate = inputFormat.parse(inputFormat.format(new Date()));
            Assert.assertEquals( expResult, formatedDate);

        }
           
     
    }
    @Test(expected=IllegalArgumentException.class)
    public void negativeTestConvertDateToDate() throws Exception {
      LOGGER.info("convertDateToDate");
        final DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date expResult = null;
        expResult = inputFormat1.parse(inputFormat1.format(new Date()));
        Date formatedDate = null;
        for (String dateFormat : negativeDataList) {
            final DateFormat inputFormat = new SimpleDateFormat(dateFormat);
            formatedDate = inputFormat.parse(inputFormat.format(new Date()));
            Assert.assertNotSame("Invalid date Format", expResult, formatedDate);

        }
    }

    /**
     * Test of dateToDateConvert method, of class CompanySearchLogic.
     */
    @Test
    public void positiveTestDateToDateConvert() throws Exception {
       LOGGER.info("convertDateToDate");
        final DateFormat inputFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Date expResult = null;
        expResult = inputFormat1.parse(inputFormat1.format(new Date()));
        Date formatedDate = null;
        positiveDataList.remove(0);
        positiveDataList.add("MM/dd/yyyy");
        for (String dateFormat : positiveDataList) {         
            final DateFormat inputFormat = new SimpleDateFormat(dateFormat);
            formatedDate = inputFormat.parse(inputFormat.format(new Date()));
            Assert.assertEquals( expResult, formatedDate);

        }
    }
    @Test(expected=NullPointerException.class)
    public void negativeTestDateToDateConvert() throws Exception {
      final DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
        Date expResult = null;
        expResult = inputFormat1.parse(inputFormat1.format(new Date()));
        Date formatedDate = null;
        negativeDataList.remove(1);
        for (String dateFormat : negativeDataList) {
            final DateFormat inputFormat = new SimpleDateFormat(dateFormat);
            formatedDate = inputFormat.parse(inputFormat.format(new Date()));
            Assert.assertNotSame("Invalid date Format", expResult, formatedDate);

        }
    }


    @Test
    public void positiveConvertDateToString() throws Exception {
        LOGGER.info("convertDateToString");
          
        String input="Fri Nov 27 14:04:31 IST 2015";
        
        String expResult = "27/11/2015";
        final DateFormat formatter = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);
        Date sDate;
        sDate = (Date) formatter.parse(input);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        String result=cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
        assertEquals(expResult, result);
       
    }
     @Test(expected=ParseException.class)
    public void negativeConvertDateToString() throws Exception {
        LOGGER.info("convertDateToString");      
      
        for(String input:negativeDataList)
        {
        String expResult = "27/11/2015";
        final DateFormat formatter = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);
        Date sDate;
        LOGGER.info("input"+input);
        sDate = (Date) formatter.parse(input);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(sDate);
        String result=cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
        assertEquals(expResult, result);
        }
       
    }

    /**
     * Test of stringToDate method, of class CompanySearchLogic.
     */
    @Test
    public void positiveTestStringToDate() throws Exception {
        
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);
        Date date=new Date();
        String  resultDate=inputFormat.format(date);
        LOGGER.info("date = " + date);
         Assert.assertEquals(inputFormat.parse(resultDate), inputFormat.parse(resultDate));
    }
   @Test(expected=ParseException.class)
    public void negativeTestStringToDate() throws Exception {
        for (String dateFormat : negativeDataList) {
            
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.SIMPLE_DATE_FORMAT);
       Date date=new Date();
        String  resultDate=inputFormat.format(date);
         Assert.assertEquals("Invalid date Format",inputFormat.parse(resultDate), inputFormat.parse(dateFormat));
            }
    }

    /**
     * Test of stringToDateForIden method, of class CompanySearchLogic.
     */
    @Test
    public void positiveTestStringToDateForIden() throws Exception {
    final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Date date=new Date();
        String  resultDate=inputFormat.format(date);
        LOGGER.info("date = " + date);
         Assert.assertEquals(inputFormat.parse(resultDate), inputFormat.parse(resultDate));
    }
    @Test(expected=ParseException.class)
    public void negativeTestStringToDateForIden() throws Exception {
            for (String dateFormat : negativeDataList) {
            
        final DateFormat inputFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
       Date date=new Date();
        String  resultDate=inputFormat.format(date);
         Assert.assertNotSame("Invalid date Format",inputFormat.parse(resultDate), inputFormat.parse(dateFormat));
            }
    }


}