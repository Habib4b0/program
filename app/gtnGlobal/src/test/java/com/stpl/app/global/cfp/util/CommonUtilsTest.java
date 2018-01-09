/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.util;

import com.stpl.app.util.CommonUIUtils;
import com.vaadin.ui.ComboBox;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jboss.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manikandaprabu.g
 */
public class CommonUtilsTest {

    private static final Logger LOGGER = Logger.getLogger(CommonUtilsTest.class);
    
    public CommonUtilsTest() {
        super();
    }

    /**
     * Test of Negative getSeletNull method, of class CommonUtils.
     */
    @Test
    public void positiveTestGetSeletNull_ComboBox() {
        LOGGER.debug("getSeletNull");
        ComboBox select = new ComboBox();
        ComboBox expResult = new ComboBox();
        ComboBox result = CommonUIUtils.getSelectNull(select);
        assertEquals(String.valueOf(expResult), String.valueOf(result));
    }

    /**
     * Test of Negative getSeletNull method, of class CommonUtils.
     */
    @Test(expected = NullPointerException.class)
    public void NegativeTestGetSeletNull_ComboBox() {
        LOGGER.debug("getSeletNull");
        ComboBox expResult = new ComboBox();
        ComboBox result = CommonUIUtils.getSelectNull(null);
        assertEquals(expResult, String.valueOf(result));
    }

    /**
     * Test of positiveTestGetDateForSession method, of class CommonUtils.
     */
    @Test
    public void positiveTestGetDateForSession() {
        LOGGER.debug("getDateForSession");
        SimpleDateFormat dt = new SimpleDateFormat("yyyyymmddhhmmssms");
        String expResult =  dt.format(new Date());
        String result = CommonUIUtils.getDateForSession();
        assertEquals(expResult, result);
    }
   

}
