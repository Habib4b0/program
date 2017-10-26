/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.common.ui.lookup;

import com.stpl.app.global.rebateschedule.dto.RSFormulaDTO;
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
public class FormulaLookupTest {
    
    private static final Logger LOGGER = Logger.getLogger(FormulaLookupTest.class);
    
    public FormulaLookupTest() {
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
     * Test of getBeanFromId method, of class FormulaLookup.
     */
    @Test
    public void testGetBeanFromId() {
        try {
            FormulaLookup instance = new FormulaLookup();
            RSFormulaDTO objec = new RSFormulaDTO();
            objec.setFormulaName("Formula");
            RSFormulaDTO result = instance.getBeanFromId(objec);
            assertEquals(null, result);
            
       } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}