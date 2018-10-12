package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkRPPopUpSearchConfigTest {
	
	@Test
	public void testGtnFrameworkRPPopUpSearchConfig_1()
		throws Exception {
		GtnFrameworkRPPopUpSearchConfig result = new GtnFrameworkRPPopUpSearchConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkRPPopUpSearchConfig fixture = new GtnFrameworkRPPopUpSearchConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("rebatePlanPopUpSearchView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("rebatePlanPopUpSearchView", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}


}