package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * The class <code>GtnFrameworkForecastConfigurationConfigTest</code> contains tests for the class <code>{@link GtnFrameworkForecastConfigurationConfig}</code>.
 *
 * @generatedBy CodePro at 8/28/18 5:41 PM
 * @author praveen.kumar
 * @version $Revision: 1.0 $
 */
public class GtnFrameworkForecastConfigurationConfigTest {
	/**
	 * Run the GtnFrameworkForecastConfigurationConfig() constructor test.
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@Test
	public void testGtnFrameworkForecastConfigurationConfig_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig result = new GtnFrameworkForecastConfigurationConfig();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void addExcelButtonLayout(List<GtnUIFrameworkComponentConfig>,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@Test
	public void testAddExcelButtonLayout_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig fixture = new GtnFrameworkForecastConfigurationConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();
		String namspacePrefix = "";

		fixture.addExcelButtonLayout(componentList, namspacePrefix);

		// add additional test code here
	}

	/**
	 * Run the GtnUIFrameworkViewConfig getMainView() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@Test
	public void testGetMainView_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig fixture = new GtnFrameworkForecastConfigurationConfig();

		GtnUIFrameworkViewConfig result = fixture.getMainView();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals(true, result.isDefaultView());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isResetAllowed());
		assertEquals("FCView", result.getViewId());
		assertEquals("FCView", result.getViewName());
	}

	/**
	 * Run the void setIntervalTextboxConfig(GtnUIFrameworkComponentConfig,GtnUIFrameworkTextBoxConfig,List<GtnUIFrameworkComponentConfig>,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 8/28/18 5:41 PM
	 */
	@Test
	public void testSetIntervalTextboxConfig_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig fixture = new GtnFrameworkForecastConfigurationConfig();
		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();
		String message = "";

		fixture.setIntervalTextboxConfig(companyIdConfig, textBoxConfig, componentList, message);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(GtnFrameworkForecastConfigurationConfigTest.class);
	}
}