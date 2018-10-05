package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkCfpLandingScreenConfigTest {
	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkCfpLandingScreenConfig fixture = new GtnFrameworkCfpLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		
		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("V001", result.getViewId());
		assertEquals(true, result.isDefaultView());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("Search View", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
	}


	@Before
	public void setUp()
		throws Exception {
		
	}

	
	@After
	public void tearDown()
		throws Exception {
		
	}

	
	
}