package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkForecastConfigurationRootConfigTest {
	@Test
	public void testGetForecastConfigurationRootConfig_1()
		throws Exception {
		GtnFrameworkForecastConfigurationRootConfig fixture = new GtnFrameworkForecastConfigurationRootConfig();

		GtnUIFrameworkRootConfig result = fixture.getForecastConfigurationRootConfig();

		// add additional test code here
		assertNotNull(result);
	}

	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkForecastConfigurationRootConfigTest.class);
	}
}