package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkIfpInformationTabConfigTest {

	@Test
	public void testGtnFrameworkIfpInformationTabConfig()
		throws Exception {
		GtnFrameworkIfpInformationTabConfig result = new GtnFrameworkIfpInformationTabConfig();
		assertNotNull(result);
	}

	@Test
	public void testAddIFPInformationTab()
		throws Exception {
		GtnFrameworkIfpInformationTabConfig fixture = new GtnFrameworkIfpInformationTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addIFPInformationTab(componentList);
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