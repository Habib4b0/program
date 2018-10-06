package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.config;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkPeriodConfigurationConfigTest {

	@Test
	public void testAddExcelButtonLayout_1()
		throws Exception {
		GtnFrameworkPeriodConfigurationConfig fixture = new GtnFrameworkPeriodConfigurationConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();
		String namspacePrefix = "";
		GtnFrameworkComponentConfigProvider componentConfigProvider = GtnFrameworkComponentConfigProvider.getInstance();

		fixture.addExcelButtonLayout(componentList, namspacePrefix, componentConfigProvider);

		// add additional test code here
	}

	@Test
	public void testCreateTableFieldFactoryComponents_1()
		throws Exception {
		GtnFrameworkPeriodConfigurationConfig fixture = new GtnFrameworkPeriodConfigurationConfig();
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}


	@Test
	public void testCreateTableFieldFactoryComponents_2()
		throws Exception {
		GtnFrameworkPeriodConfigurationConfig fixture = new GtnFrameworkPeriodConfigurationConfig();
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}


	@Test
	public void testCreateTableFieldFactoryComponents_3()
		throws Exception {
		GtnFrameworkPeriodConfigurationConfig fixture = new GtnFrameworkPeriodConfigurationConfig();
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

		// add additional test code here
		assertNotNull(result);
		assertEquals(0, result.size());
	}


	@Test
	public void testGetMainView_1()
		throws Exception {
		GtnFrameworkPeriodConfigurationConfig fixture = new GtnFrameworkPeriodConfigurationConfig();

		GtnUIFrameworkViewConfig result = fixture.getMainView();

		assertNotNull(result);
		assertEquals("PCView", result.getViewId());
		assertEquals(true, result.isResetAllowed());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals("PCView", result.getViewName());
		assertEquals(true, result.isDefaultView());
		assertEquals(null, result.getViewActionList());
	}

}