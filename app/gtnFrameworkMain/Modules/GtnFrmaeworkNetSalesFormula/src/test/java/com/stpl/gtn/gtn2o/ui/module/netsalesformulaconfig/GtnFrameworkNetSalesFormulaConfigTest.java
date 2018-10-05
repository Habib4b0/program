package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkNetSalesFormulaConfigTest {
	/**
	 * Run the GtnUIFrameworkRootConfig getNetSalesFormulaRootConfig() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testGetNetSalesFormulaRootConfig_1()
		throws Exception {
		GtnFrameworkNetSalesFormulaConfig fixture = new GtnFrameworkNetSalesFormulaConfig();

		GtnUIFrameworkRootConfig result = fixture.getNetSalesFormulaRootConfig();

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