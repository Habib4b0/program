package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;

/**

 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkNSFDeductionTabConfigTest {
	/**
	 * Run the GtnFrameworkNSFDeductionTabConfig() constructor test.
	 *
	
	 */
	@Test
	public void testGtnFrameworkNSFDeductionTabConfig_1()
		throws Exception {
		GtnFrameworkNSFDeductionTabConfig result = new GtnFrameworkNSFDeductionTabConfig();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the GtnUIFrameworkTabConfig addDeductionTabConfig(String) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testAddDeductionTabConfig_1()
		throws Exception {
		GtnFrameworkNSFDeductionTabConfig fixture = new GtnFrameworkNSFDeductionTabConfig();
		String viewId = "";

		GtnUIFrameworkTabConfig result = fixture.addDeductionTabConfig(viewId);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testCreateTableFieldFactoryComponents_1()
		throws Exception {
		GtnFrameworkNSFDeductionTabConfig fixture = new GtnFrameworkNSFDeductionTabConfig();

		List<GtnUIFrameworkComponentConfig> result = fixture.createTableFieldFactoryComponents();

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