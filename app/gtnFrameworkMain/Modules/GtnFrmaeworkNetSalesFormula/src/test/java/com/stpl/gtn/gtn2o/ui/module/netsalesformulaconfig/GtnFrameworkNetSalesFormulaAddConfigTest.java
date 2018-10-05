package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**

 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkNetSalesFormulaAddConfigTest {
	/**
	 * Run the GtnFrameworkNetSalesFormulaAddConfig() constructor test.
	 *
	
	 */
	@Test
	public void testGtnFrameworkNetSalesFormulaAddConfig_1()
		throws Exception {
		GtnFrameworkNetSalesFormulaAddConfig result = new GtnFrameworkNetSalesFormulaAddConfig();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the GtnUIFrameworkViewConfig getAddView() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGetAddView_1()
		throws Exception {
		GtnFrameworkNetSalesFormulaAddConfig fixture = new GtnFrameworkNetSalesFormulaAddConfig();

		GtnUIFrameworkViewConfig result = fixture.getAddView();

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