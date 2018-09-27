package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkIfpConfigTest {

	@Test
	public void testGetIFPRootConfig()
		throws Exception {
		
		GtnFrameworkIfpConfig fixture = new GtnFrameworkIfpConfig();
		GtnUIFrameworkRootConfig result = fixture.getIFPRootConfig();
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