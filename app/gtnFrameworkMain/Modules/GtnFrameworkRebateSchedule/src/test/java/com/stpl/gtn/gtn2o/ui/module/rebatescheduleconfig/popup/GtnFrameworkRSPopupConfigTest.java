package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkRSPopupConfigTest {
	
	@Test
	public void testGtnFrameworkRSPopupConfig_1()
		throws Exception {
		GtnFrameworkRSPopupConfig result = new GtnFrameworkRSPopupConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testGetView_1()
		throws Exception {
		GtnFrameworkRSPopupConfig fixture = new GtnFrameworkRSPopupConfig();

		GtnUIFrameworkViewConfig result = fixture.getView();

		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("RSPopUpView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("RSPopUpView", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}


}