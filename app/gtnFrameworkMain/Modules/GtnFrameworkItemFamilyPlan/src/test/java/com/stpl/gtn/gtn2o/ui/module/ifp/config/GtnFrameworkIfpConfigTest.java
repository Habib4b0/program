package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkIfpConfigTest {

	@Test
	public void testGetIFPRootConfig_1()
		throws Exception {
		GtnFrameworkIfpConfig fixture = new GtnFrameworkIfpConfig();

		GtnUIFrameworkRootConfig result = fixture.getIFPRootConfig();

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
		new org.junit.runner.JUnitCore().run(GtnFrameworkIfpConfigTest.class);
	}
}