/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.constants;

import static com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants.GTN_PROCESS_MONITOR_PROCESS_NAME;
import static com.stpl.gtn.gtn2o.ui.module.processmonitor.constants.GtnFrameworkProcessMonitorStringContants.PROCESS_TYPE;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnFrameworkProcessMonitorStringContantsTest {
    
    public GtnFrameworkProcessMonitorStringContantsTest() {
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
     * Test of getCalendar method, of class GtnFrameworkProcessMonitorStringContants.
     */
    @Test
    public void testGetCalendar() {
        System.out.println("getCalendar");
        String[] expResult = { "Holiday" };
        String[] result = GtnFrameworkProcessMonitorStringContants.getCalendar();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getProcessMonitorTableColumns method, of class GtnFrameworkProcessMonitorStringContants.
     */
    @Test
    public void testGetProcessMonitorTableColumns() {
        System.out.println("getProcessMonitorTableColumns");
        Object[] expResult = { GTN_PROCESS_MONITOR_PROCESS_NAME, PROCESS_TYPE,
			"slaCalendarMasterSid", "modifiedDate", "modifiedBy" };
        Object[] result = GtnFrameworkProcessMonitorStringContants.getProcessMonitorTableColumns();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getProcessMonitorTableHeader method, of class GtnFrameworkProcessMonitorStringContants.
     */
    @Test
    public void testGetProcessMonitorTableHeader() {
        System.out.println("getProcessMonitorTableHeader");
        String[] expResult = { "Monitor Name", "Process Type",
			"Calendar", "Modified Date", "Modified By" };
        String[] result = GtnFrameworkProcessMonitorStringContants.getProcessMonitorTableHeader();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getProcessMonitorTableColumnType method, of class GtnFrameworkProcessMonitorStringContants.
     */
    @Test
    public void testGetProcessMonitorTableColumnType() {
        System.out.println("getProcessMonitorTableColumnType");
        Class[] expResult = {
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
			GtnFrameworkCommonConstants.JAVA_LANG_STRING };
        Class[] result = GtnFrameworkProcessMonitorStringContants.getProcessMonitorTableColumnType();
        assertArrayEquals(expResult, result);
    }
    
}
