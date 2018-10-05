package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;



public class GtnFrameworkCfpCompaniesTabConfigTest {
	
	@Test
	public void testAddCompaniesTab_1()
		throws Exception {
		GtnFrameworkCfpCompaniesTabConfig fixture = new GtnFrameworkCfpCompaniesTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addCompaniesTab(componentList);

		
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_1()
		throws Exception {
		GtnFrameworkCfpCompaniesTabConfig fixture = new GtnFrameworkCfpCompaniesTabConfig();
		List<String> propertyIds = new LinkedList();
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds, componentConfig);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_2()
		throws Exception {
		GtnFrameworkCfpCompaniesTabConfig fixture = new GtnFrameworkCfpCompaniesTabConfig();
		List<String> propertyIds = new LinkedList();
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds, componentConfig);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_3()
		throws Exception {
		GtnFrameworkCfpCompaniesTabConfig fixture = new GtnFrameworkCfpCompaniesTabConfig();
		List<String> propertyIds = new LinkedList();
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents(propertyIds, componentConfig);

		
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Before
	public void setUp()
		throws Exception {
	
	}

	
	@After
	public void tearDown()
		throws Exception {
		
	}

	
	
}