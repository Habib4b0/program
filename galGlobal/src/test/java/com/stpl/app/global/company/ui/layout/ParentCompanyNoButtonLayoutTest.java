/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.layout;

import com.stpl.app.global.company.dto.SearchDTO;
import com.vaadin.data.util.BeanItem;
import org.jboss.logging.Logger;
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
public class ParentCompanyNoButtonLayoutTest {
    
     private static final Logger LOGGER = Logger.getLogger(ParentCompanyNoButtonLayoutTest.class);
    
    public ParentCompanyNoButtonLayoutTest() {
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
     * Test of getBeanFromId method, of class ParentCompanyNoButtonLayout.
     */
    @Test
    public void testGetBeanFromId() {
        LOGGER.info("testGetBeanFromId");
        SearchDTO expResult=new SearchDTO();
        BeanItem<SearchDTO> bean=new BeanItem<>(expResult);
        SearchDTO result;
         Object identifier = bean;
          BeanItem<?> targetItem = null;
        if (identifier instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) identifier;
        } else if (identifier instanceof SearchDTO) {
            targetItem = new BeanItem<SearchDTO>((SearchDTO) identifier);
        }
        result=(SearchDTO) targetItem.getBean();
        assertEquals(expResult, result);

    }
   @Test
    public void negativeTstGetBeanFromId() {
        LOGGER.info("negativeTstGetBeanFromId");
        SearchDTO expResult=new SearchDTO();
        BeanItem<SearchDTO> bean=new BeanItem<>(expResult);
        SearchDTO result;
         Object identifier = bean;
          BeanItem<?> targetItem = null;
        if (identifier instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) identifier;
        } else if (identifier instanceof SearchDTO) {
            targetItem = new BeanItem<SearchDTO>((SearchDTO) identifier);
        }
        result=(SearchDTO) targetItem.getBean();
        assertEquals(expResult, result);

    }

   
}