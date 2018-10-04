package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkNSSearchConfigTest {
	
	@Test
	public void testGtnFrameworkNSSearchConfig_1()
		throws Exception {
		GtnFrameworkNSSearchConfig result = new GtnFrameworkNSSearchConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkNSSearchConfig fixture = new GtnFrameworkNSSearchConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("netSalesFormulaPopUpView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("netSalesFormulaPopUpView", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}


}