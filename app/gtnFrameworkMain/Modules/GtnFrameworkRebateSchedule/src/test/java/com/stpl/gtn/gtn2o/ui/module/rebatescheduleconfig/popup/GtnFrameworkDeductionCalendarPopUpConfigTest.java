package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkDeductionCalendarPopUpConfigTest {
	
	@Test
	public void testGtnFrameworkDeductionCalendarPopUpConfig_1()
		throws Exception {
		GtnFrameworkDeductionCalendarPopUpConfig result = new GtnFrameworkDeductionCalendarPopUpConfig();
		assertNotNull(result);
		
	}

	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkDeductionCalendarPopUpConfig fixture = new GtnFrameworkDeductionCalendarPopUpConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		
		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("deductionCalendarPopUpSearchView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isResetAllowed());
		assertEquals("deductionCalendarPopUpSearchView", result.getViewName());
		assertEquals(false, result.isDefaultView());
	}

	

	
	
}