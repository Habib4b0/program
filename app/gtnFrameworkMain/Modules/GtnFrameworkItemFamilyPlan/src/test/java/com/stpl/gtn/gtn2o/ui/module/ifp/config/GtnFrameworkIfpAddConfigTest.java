package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkIfpAddConfigTest {

	@Test
	public void testGtnFrameworkIfpAddConfig()
		throws Exception {
		GtnFrameworkIfpAddConfig result = new GtnFrameworkIfpAddConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetIFPAddView()
		throws Exception {
		GtnFrameworkIfpAddConfig fixture = new GtnFrameworkIfpAddConfig();

		GtnUIFrameworkViewConfig result = fixture.getIFPAddView();

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