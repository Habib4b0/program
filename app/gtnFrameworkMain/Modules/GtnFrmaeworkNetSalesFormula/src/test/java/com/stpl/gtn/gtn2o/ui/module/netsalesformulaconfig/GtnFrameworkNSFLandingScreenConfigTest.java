package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**

 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkNSFLandingScreenConfigTest {
	/**
	 * Run the GtnFrameworkNSFLandingScreenConfig() constructor test.
	 *
	
	 */
	@Test
	public void testGtnFrameworkNSFLandingScreenConfig_1()
		throws Exception {
		GtnFrameworkNSFLandingScreenConfig result = new GtnFrameworkNSFLandingScreenConfig();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the GtnUIFrameworkViewConfig getSearchView() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkNSFLandingScreenConfig fixture = new GtnFrameworkNSFLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		// add additional test code here
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