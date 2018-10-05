package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;


public class GtnFrameworkCfpCompanyAdditionTabConfigTest {
	
	@Test
	public void testAddCompanyAdditionTab_1()
		throws Exception {
		GtnFrameworkCfpCompanyAdditionTabConfig fixture = new GtnFrameworkCfpCompanyAdditionTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addCompanyAdditionTab(componentList);

		
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