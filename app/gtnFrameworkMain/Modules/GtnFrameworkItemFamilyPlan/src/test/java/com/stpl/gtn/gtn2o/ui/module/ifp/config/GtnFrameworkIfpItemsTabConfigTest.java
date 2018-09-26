package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkIfpItemsTabConfigTest {

	@Test
	public void testGtnFrameworkIfpItemsTabConfig()
		throws Exception {
		GtnFrameworkIfpItemsTabConfig result = new GtnFrameworkIfpItemsTabConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testAddItemsTab()
		throws Exception {
		
		GtnFrameworkIfpItemsTabConfig fixture = new GtnFrameworkIfpItemsTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList<GtnUIFrameworkComponentConfig>();
		fixture.addItemsTab(componentList);

	}

	@Test
	public void testCreateTableFieldFactoryComponents_1()
		throws Exception {
		GtnFrameworkIfpItemsTabConfig fixture = new GtnFrameworkIfpItemsTabConfig();
		List<String> propertyIds = new LinkedList<String>();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
	}

	@Test
	public void testCreateTableFieldFactoryComponents_2()
		throws Exception {
		GtnFrameworkIfpItemsTabConfig fixture = new GtnFrameworkIfpItemsTabConfig();
		List<String> propertyIds = new LinkedList<String>();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testCreateTableFieldFactoryComponents_3()
		throws Exception {
		GtnFrameworkIfpItemsTabConfig fixture = new GtnFrameworkIfpItemsTabConfig();
		List<String> propertyIds = new LinkedList<String>();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testCreateTableFieldFactoryComponents_4()
		throws Exception {
		GtnFrameworkIfpItemsTabConfig fixture = new GtnFrameworkIfpItemsTabConfig();
		List<String> propertyIds = new LinkedList<String>();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds);

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

}