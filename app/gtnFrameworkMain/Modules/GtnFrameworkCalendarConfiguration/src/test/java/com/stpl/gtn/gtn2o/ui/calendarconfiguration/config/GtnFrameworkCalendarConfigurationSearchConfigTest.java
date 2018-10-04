package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCalendarConfigurationSearchConfigTest {

	@Test
	public void testGtnFrameworkCalendarConfigurationSearchConfig_1()
		throws Exception {
		GtnFrameworkCalendarConfigurationSearchConfig result = new GtnFrameworkCalendarConfigurationSearchConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetMainView_1()
		throws Exception {
		GtnFrameworkCalendarConfigurationSearchConfig fixture = new GtnFrameworkCalendarConfigurationSearchConfig();
		GtnUIFrameworkViewConfig result = fixture.getMainView();
		assertNotNull(result);
		assertEquals("CCMainView", result.getViewId());
		assertEquals(true, result.isDefaultView());
		assertEquals(true, result.isResetAllowed());
		assertEquals("CCMainView", result.getViewName());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(null, result.getViewActionList());
	}

}