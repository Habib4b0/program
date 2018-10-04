package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkRPLandingScreenConfigTest {

	@Test
	public void testGtnFrameworkRPLandingScreenConfig_1()
		throws Exception {
		GtnFrameworkRPLandingScreenConfig result = new GtnFrameworkRPLandingScreenConfig();
		assertNotNull(result);
	}


	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkRPLandingScreenConfig fixture = new GtnFrameworkRPLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals("rebatePlanSearchView", result.getViewId());
		assertEquals(true, result.isDefaultView());
		assertEquals(null, result.getViewActionList());
		assertEquals("rebatePlanSearchView", result.getViewName());
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
		new org.junit.runner.JUnitCore().run(GtnFrameworkRPLandingScreenConfigTest.class);
	}
}