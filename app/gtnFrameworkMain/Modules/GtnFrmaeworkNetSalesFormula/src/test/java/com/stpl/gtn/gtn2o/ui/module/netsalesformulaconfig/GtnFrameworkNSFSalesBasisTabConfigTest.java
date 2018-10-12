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
public class GtnFrameworkNSFSalesBasisTabConfigTest {
	/**
	 * Run the GtnFrameworkNSFSalesBasisTabConfig() constructor test.
	 *
	
	 */
	@Test
	public void testGtnFrameworkNSFSalesBasisTabConfig_1()
		throws Exception {
		GtnFrameworkNSFSalesBasisTabConfig result = new GtnFrameworkNSFSalesBasisTabConfig();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the GtnUIFrameworkTabConfig addSalesBasisTabConfig(String) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testAddSalesBasisTabConfig_1()
		throws Exception {
		GtnFrameworkNSFSalesBasisTabConfig fixture = new GtnFrameworkNSFSalesBasisTabConfig();
		String viewId = "";

		GtnUIFrameworkTabConfig result = fixture.addSalesBasisTabConfig(viewId);

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
		GtnFrameworkNSFSalesBasisTabConfig fixture = new GtnFrameworkNSFSalesBasisTabConfig();

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