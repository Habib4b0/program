package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;


public class GtnFrameworkCfpConfigTest {
	
	@Test
	public void testGetCFPRootConfig_1()
		throws Exception {
		GtnFrameworkCfpConfig fixture = new GtnFrameworkCfpConfig();

		GtnUIFrameworkRootConfig result = fixture.getCFPRootConfig();

		
		assertNotNull(result);
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