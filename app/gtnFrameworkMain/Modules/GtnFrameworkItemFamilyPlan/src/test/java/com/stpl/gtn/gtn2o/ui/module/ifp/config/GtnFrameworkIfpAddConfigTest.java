package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkIfpAddConfigTest {

	@Test
	public void testGtnFrameworkIfpAddConfig_1()
		throws Exception {
		GtnFrameworkIfpAddConfig result = new GtnFrameworkIfpAddConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testGetIFPAddView_1()
		throws Exception {
		GtnFrameworkIfpAddConfig fixture = new GtnFrameworkIfpAddConfig();

		GtnUIFrameworkViewConfig result = fixture.getIFPAddView();

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
		new org.junit.runner.JUnitCore().run(GtnFrameworkIfpAddConfigTest.class);
	}
}