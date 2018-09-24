package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkIfpInformationTabConfigTest {

	@Test
	public void testGtnFrameworkIfpInformationTabConfig_1()
		throws Exception {
		GtnFrameworkIfpInformationTabConfig result = new GtnFrameworkIfpInformationTabConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testAddIFPInformationTab_1()
		throws Exception {
		GtnFrameworkIfpInformationTabConfig fixture = new GtnFrameworkIfpInformationTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addIFPInformationTab(componentList);

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(GtnFrameworkIfpInformationTabConfigTest.class);
	}
}