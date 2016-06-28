/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.util;

import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.ui.ComboBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manikandaprabu.g
 */
public class CommonUtilsTest {

    private static final Logger LOGGER = Logger.getLogger(CommonUtilsTest.class);
    
    public CommonUtilsTest() {
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
     * Test of Positive searchFields method, of class CommonUtils.
     */
    @Test
    public void positiveTestSearchFields() {
        LOGGER.info("searchFields");
        CommonUtils instance = new CommonUtils();
        final List<String> list = new ArrayList<String>();
        list.add(ConstantsUtils.SELECT_ONE);
        list.add("Company Name");
        list.add("Company No");
        list.add("Company Status");
        list.add("Company Type");
        Container result = instance.searchFields();
        final Collection<?> actual = result.getItemIds();
        assertEquals(list,actual);

    }

    /**
     * Test of Negative getSeletNull method, of class CommonUtils.
     */
    @Test
    public void positiveTestGetSeletNull_ComboBox() {
        LOGGER.info("getSeletNull");
        ComboBox select = new ComboBox();
        ComboBox expResult = new ComboBox();
        ComboBox result = CommonUtils.getSeletNull(select);
        assertEquals(String.valueOf(expResult), String.valueOf(result));
    }

    /**
     * Test of Negative getSeletNull method, of class CommonUtils.
     */
    @Test(expected = NullPointerException.class)
    public void NegativeTestGetSeletNull_ComboBox() {
        LOGGER.info("getSeletNull");
        ComboBox expResult = new ComboBox();
        ComboBox result = CommonUtils.getSeletNull(null);
        assertEquals(expResult, String.valueOf(result));
    }

    /**
     * Test of positiveTestGetDateForSession method, of class CommonUtils.
     */
    @Test
    public void positiveTestGetDateForSession() {
        LOGGER.info("getDateForSession");
        SimpleDateFormat dt = new SimpleDateFormat("yyyyymmddhhmmssms");
        String expResult =  dt.format(new Date());
        String result = CommonUtils.getDateForSession();
        assertEquals(expResult, result);
    }
   

}
