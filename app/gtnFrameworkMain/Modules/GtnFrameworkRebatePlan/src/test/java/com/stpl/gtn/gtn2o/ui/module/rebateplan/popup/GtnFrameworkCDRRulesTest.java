package com.stpl.gtn.gtn2o.ui.module.rebateplan.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkCDRRulesTest {

	@Test
	public void testGtnFrameworkCDRRules_1()
		throws Exception {
		GtnFrameworkCDRRules result = new GtnFrameworkCDRRules();
		assertNotNull(result);
	}


	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkCDRRules fixture = new GtnFrameworkCDRRules();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals("CDRPopUpSearchSearchView", result.getViewId());
		assertEquals(false, result.isDefaultView());
		assertEquals(null, result.getViewActionList());
		assertEquals("CDRPopUpSearchSearchView", result.getViewName());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isResetAllowed());
	}

	@Before
	public void setUp()
		throws Exception {
	}


	@After
	public void tearDown()
		throws Exception {
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkCDRRulesTest.class);
	}
}