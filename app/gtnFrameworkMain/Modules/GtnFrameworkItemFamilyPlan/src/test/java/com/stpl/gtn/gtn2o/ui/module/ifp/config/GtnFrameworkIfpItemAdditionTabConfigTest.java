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

@Ignore
public class GtnFrameworkIfpItemAdditionTabConfigTest {

	@Test
	public void testGtnFrameworkIfpItemAdditionTabConfig_1()
		throws Exception {
		GtnFrameworkIfpItemAdditionTabConfig result = new GtnFrameworkIfpItemAdditionTabConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testAddItemAdditionTab_1()
		throws Exception {
		GtnFrameworkIfpItemAdditionTabConfig fixture = new GtnFrameworkIfpItemAdditionTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addItemAdditionTab(componentList);

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
		new org.junit.runner.JUnitCore().run(GtnFrameworkIfpItemAdditionTabConfigTest.class);
	}
}