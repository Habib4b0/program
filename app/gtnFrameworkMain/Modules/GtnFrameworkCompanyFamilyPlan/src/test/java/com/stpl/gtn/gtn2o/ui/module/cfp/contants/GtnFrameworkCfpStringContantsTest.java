package com.stpl.gtn.gtn2o.ui.module.cfp.contants;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class GtnFrameworkCfpStringContantsTest {

	@Test
	public void testgetLandingScreenpropertyIds() {
		Object[] result = GtnFrameworkCfpStringContants.getLandingScreenpropertyIds();
		assertNotNull(result);
	}
	@Test
	public void testgetCustomListNameList()
		throws Exception {

		Object[] result = GtnFrameworkCfpStringContants.getCustomListNameList();
		assertNotNull(result);
	}

	@Test
	public void testgetLandingScreenListNameArray()
		throws Exception {

		Object[] result = GtnFrameworkCfpStringContants.getLandingScreenListNameArray();
		assertNotNull(result);
	}

	@Test
	public void testgetPropertyIds()
		throws Exception {

		String[] result = GtnFrameworkCfpStringContants.getPropertyIds();
		assertNotNull(result);
	}

	@Test
	public void testgetListNameList()
		throws Exception {

		String[] result = GtnFrameworkCfpStringContants.getListNameList();
		assertNotNull(result);
	}

	@Test
	public void testgetCfpValidateFields()
		throws Exception {

		String[] result = GtnFrameworkCfpStringContants.getCfpValidateFields();
		assertNotNull(result);
	}

	@Test
	public void testgetCustomFilterPropertyIds()
		throws Exception {

		String[] result = GtnFrameworkCfpStringContants.getCustomFilterPropertyIds();
		assertNotNull(result);
	}
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}
}
