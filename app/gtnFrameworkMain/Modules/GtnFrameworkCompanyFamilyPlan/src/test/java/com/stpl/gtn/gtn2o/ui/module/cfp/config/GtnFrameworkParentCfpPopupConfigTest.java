package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkParentCfpPopupConfigTest {
	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkParentCfpPopupConfig fixture = new GtnFrameworkParentCfpPopupConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		
		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("parentCfpView", result.getViewId());
		assertEquals(false, result.isDefaultView());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("Parent Company Family Plan", result.getViewName());
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