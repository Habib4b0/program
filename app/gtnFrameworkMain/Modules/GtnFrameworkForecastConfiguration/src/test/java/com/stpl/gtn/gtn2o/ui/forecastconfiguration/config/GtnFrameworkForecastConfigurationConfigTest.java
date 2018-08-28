package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkForecastConfigurationConfigTest {

	@Test
	public void testGtnFrameworkForecastConfigurationConfig_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig result = new GtnFrameworkForecastConfigurationConfig();
		assertNotNull(result);

	}

	@Test
	public void testAddExcelButtonLayout_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig fixture = new GtnFrameworkForecastConfigurationConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();
		String namspacePrefix = "";

		fixture.addExcelButtonLayout(componentList, namspacePrefix);

	
	}

	@Test
	public void testGetMainView_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig fixture = new GtnFrameworkForecastConfigurationConfig();

		GtnUIFrameworkViewConfig result = fixture.getMainView();

	
		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals(true, result.isDefaultView());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isResetAllowed());
		assertEquals("FCView", result.getViewId());
		assertEquals("FCView", result.getViewName());
	}

	@Test
	public void testSetIntervalTextboxConfig_1()
		throws Exception {
		GtnFrameworkForecastConfigurationConfig fixture = new GtnFrameworkForecastConfigurationConfig();
		GtnUIFrameworkComponentConfig companyIdConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();
		String message = "";

		fixture.setIntervalTextboxConfig(companyIdConfig, textBoxConfig, componentList, message);

		
	}


	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkForecastConfigurationConfigTest.class);
	}
}