package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkRebateSchedulePopUpConfigTest {
	
	@Test
	public void testGtnFrameworkRebateSchedulePopUpConfig_1()
		throws Exception {
		GtnFrameworkRebateSchedulePopUpConfig result = new GtnFrameworkRebateSchedulePopUpConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkRebateSchedulePopUpConfig fixture = new GtnFrameworkRebateSchedulePopUpConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("RebateSchedulePopUpConfig", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("RebateSchedulePopUpConfig", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}

	

}