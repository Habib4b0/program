package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCalendarConfigurationCrudConfigTest {

	@Test
	public void testGtnFrameworkCalendarConfigurationCrudConfig_1()
		throws Exception {
		GtnFrameworkCalendarConfigurationCrudConfig result = new GtnFrameworkCalendarConfigurationCrudConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetCalendarConfigView_1()
		throws Exception {
		GtnFrameworkCalendarConfigurationCrudConfig fixture = new GtnFrameworkCalendarConfigurationCrudConfig();
		GtnUIFrameworkViewConfig result = fixture.getCalendarConfigView();
		assertNotNull(result);
		assertEquals("CCCrudView", result.getViewId());
		assertEquals(false, result.isDefaultView());
		assertEquals(true, result.isResetAllowed());
		assertEquals("CCCrudView", result.getViewName());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(null, result.getViewActionList());
	}

}