package com.stpl.gtn.gtn2o.ui.module.udc.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkUDCConfigurationLandingScreenConfigTest {

	@Test
	public void testGetUDCView_1()
		throws Exception {
		GtnFrameworkUDCConfigurationLandingScreenConfig fixture = new GtnFrameworkUDCConfigurationLandingScreenConfig();
		GtnUIFrameworkViewConfig result = fixture.getUDCView();
		assertNotNull(result);
		assertEquals("UDC001", result.getViewId());
		assertEquals(true, result.isResetAllowed());
		assertEquals("Udc View", result.getViewName());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isDefaultView());
		assertEquals(null, result.getViewActionList());
	}
}