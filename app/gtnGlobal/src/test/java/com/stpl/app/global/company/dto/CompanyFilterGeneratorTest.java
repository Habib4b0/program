/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.unittest.testcases;

import com.stpl.app.global.company.dto.CompanyFilterGenerator;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karthikraja.k
 */
public class CompanyFilterGeneratorTest {
      List<Object> positiveDataList;
       List<Object> negativeDataList;
    public CompanyFilterGeneratorTest() {
     
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        positiveDataList=new ArrayList<Object>();
        negativeDataList=new ArrayList<Object>();
        positiveDataList.add("10/10/1995");
        positiveDataList.add("20/05/2000");
        positiveDataList.add("10-10-1996");
        positiveDataList.add("23/10/1996 50:50:50");
        negativeDataList.add(null);
        negativeDataList.add("");
        negativeDataList.add(this);
        negativeDataList.add("kllas");
        negativeDataList.add("8822717*&8");
        negativeDataList.add("eooYH@*((O");
        negativeDataList.add(new Integer(50));
        negativeDataList.add(new Boolean(true));
        negativeDataList.add(new Boolean(false));
        negativeDataList.add(new String());
        negativeDataList.add(new Byte(Byte.MAX_VALUE));
        negativeDataList.add(new Byte(Byte.MIN_VALUE));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateFilter method, of class CompanyFilterGenerator.
     */
    @Test
    public void testGenerateFilter_Object_Object() {
        for(Object value: positiveDataList)
        {
        Object property = "startDate";
        CompanyFilterGenerator instance = new CompanyFilterGenerator();
        Container.Filter result = instance.generateFilter(property, value);
        assertEquals(result, result);
        }
       
    }
    @Test
    public void negativeTest1GenerateFilter_Object_Object() {
        for(Object prop: negativeDataList)
        {
        Object value = "23/10/2014";
        CompanyFilterGenerator instance = new CompanyFilterGenerator();
        Container.Filter result = instance.generateFilter(prop, value);
        assertEquals(null, result);
        }
       
    }
     @Test
    public void negativeTest2GenerateFilter_Object_Object() {
        for(Object value: negativeDataList)
        {
        Object prop = "startDate";
        CompanyFilterGenerator instance = new CompanyFilterGenerator();
        Container.Filter result = instance.generateFilter(prop, value);
        assertEquals(null, result);
        }
       
    }

    /**
     * Test of generateFilter method, of class CompanyFilterGenerator.
     */
    @Test
    public void positivTest1GenerateFilter_Object_Field() {
        positiveDataList.removeAll(positiveDataList);
        positiveDataList.add("tradeClass");
        positiveDataList.add("companyStatus");
        for(Object propertyId: positiveDataList)
        {
        Field<?> originatingField = new ComboBox();
        CompanyFilterGenerator instance = new CompanyFilterGenerator();
        Container.Filter expResult = null;
        Container.Filter result = instance.generateFilter(propertyId, originatingField);
        assertEquals(expResult, result);
        }
       
    }
   @Test
    public void negativeGenerateFilter_Object_Field() {

        for(Object propertyId: negativeDataList)
        {
         Field<?> originatingField = new ComboBox();
        CompanyFilterGenerator instance = new CompanyFilterGenerator();
        Container.Filter expResult = null;
        Container.Filter result = instance.generateFilter(propertyId, originatingField);
        assertEquals(expResult, result);
        }
       
    }

 
}