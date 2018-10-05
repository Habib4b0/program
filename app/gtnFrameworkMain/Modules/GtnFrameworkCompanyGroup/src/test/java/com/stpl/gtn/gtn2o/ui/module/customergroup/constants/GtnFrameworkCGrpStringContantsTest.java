package com.stpl.gtn.gtn2o.ui.module.customergroup.constants;

import org.junit.*;
import static org.junit.Assert.*;


public class GtnFrameworkCGrpStringContantsTest {
	
	@Test
	public void testGetAddSearchDisableField(){

		Object[] result = GtnFrameworkCGrpStringContants.getAddSearchDisableField();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
	}

	
	@Test
	public void testGetAuditSearchDisableField(){

		Object[] result = GtnFrameworkCGrpStringContants.getAuditSearchDisableField();

		assertNotNull(result);
		assertEquals(3, result.length);
		assertNotNull(result[0]);
		assertNotNull(result[1]);
		assertNotNull(result[2]);
	}


	@Test
	public void testGetComponentidArray(){

		String[] result = GtnFrameworkCGrpStringContants.getComponentidArray();

		assertNotNull(result);
		assertEquals(8, result.length);
		assertEquals("cGrpInformationTabCustomerNo", result[0]);
		assertEquals("cGrpInformationTabTradeClass", result[1]);
		assertEquals("cGrpInformationTabCustomerStatus", result[2]);
		assertEquals("cGrpInformationTabState", result[3]);
		assertEquals("cGrpInformationTabCustomerName", result[4]);
		assertEquals("cGrpInformationTabCustomerType", result[5]);
		assertEquals("cGrpInformationTabCity", result[6]);
		assertEquals("cGrpInformationTabZipcode", result[7]);
	}

	
	@Test
	public void testGetResetComponentidArray(){

		String[] result = GtnFrameworkCGrpStringContants.getResetComponentidArray();

		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals("customerGroupName", result[0]);
		assertEquals("customerGroupNo", result[1]);
		assertEquals("customerGroupDesc", result[2]);
		assertEquals("cGrpsearchResultTable", result[3]);
	}

}