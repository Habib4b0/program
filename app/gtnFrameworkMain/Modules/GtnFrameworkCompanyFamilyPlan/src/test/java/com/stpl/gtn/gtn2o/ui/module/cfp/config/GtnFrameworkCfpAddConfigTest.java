package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;



 
public class GtnFrameworkCfpAddConfigTest {
	
	@Test
	public void testGetCFPAddView_1()
		throws Exception {
		GtnFrameworkCfpAddConfig fixture = new GtnFrameworkCfpAddConfig();

		GtnUIFrameworkViewConfig result = fixture.getCFPAddView();

		
		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("V002", result.getViewId());
		assertEquals(false, result.isDefaultView());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("Add View", result.getViewName());
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