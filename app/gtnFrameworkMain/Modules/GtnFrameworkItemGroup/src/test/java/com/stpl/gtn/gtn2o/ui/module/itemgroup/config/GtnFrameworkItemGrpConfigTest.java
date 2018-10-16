package com.stpl.gtn.gtn2o.ui.module.itemgroup.config;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkItemGrpConfigTest {
	/**
	 * Run the GtnUIFrameworkRootConfig getIGrpRootConfig() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetIGrpRootConfig_1()
		throws Exception {
		
		GtnFrameworkItemGrpConfig fixture = new GtnFrameworkItemGrpConfig();
		GtnUIFrameworkRootConfig result = fixture.getIGrpRootConfig();
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}