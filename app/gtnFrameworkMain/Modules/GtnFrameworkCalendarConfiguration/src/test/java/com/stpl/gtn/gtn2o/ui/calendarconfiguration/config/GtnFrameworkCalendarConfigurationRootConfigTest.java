package com.stpl.gtn.gtn2o.ui.calendarconfiguration.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCalendarConfigurationRootConfigTest {
	@Test
	public void testGetContractDashboardRootConfig_1()
		throws Exception {
		GtnFrameworkCalendarConfigurationRootConfig fixture = new GtnFrameworkCalendarConfigurationRootConfig();
		GtnUIFrameworkRootConfig result = fixture.getContractDashboardRootConfig();
		assertNotNull(result);
	}
}