package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * The class <code>GtnFrameworkForecastConfigurationRootConfigTest</code> contains tests for the class <code>{@link GtnFrameworkForecastConfigurationRootConfig}</code>.
 *
 * @generatedBy CodePro at 8/28/18 5:41 PM
 * @author praveen.kumar
 * @version $Revision: 1.0 $
 */
public class GtnFrameworkForecastConfigurationRootConfigTest {
	/**
	 * Run the GtnUIFrameworkRootConfig getForecastConfigurationRootConfig() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@Test
	public void testGetForecastConfigurationRootConfig_1()
		throws Exception {
		GtnFrameworkForecastConfigurationRootConfig fixture = new GtnFrameworkForecastConfigurationRootConfig();

		GtnUIFrameworkRootConfig result = fixture.getForecastConfigurationRootConfig();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkForecastConfigurationRootConfigTest.class);
	}
}