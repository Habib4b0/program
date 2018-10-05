package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

public class GtnFrameworkRebatePlanConfigTest {

	@Test
	public void testGetRebatePlanRootConfig_1()
		throws Exception {
		GtnFrameworkRebatePlanConfig fixture = new GtnFrameworkRebatePlanConfig();

		GtnUIFrameworkRootConfig result = fixture.getRebatePlanRootConfig();

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


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkRebatePlanConfigTest.class);
	}
}