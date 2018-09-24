package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkParentIfpPopupConfigTest {
	
	@Test
	public void testGtnFrameworkParentIfpPopupConfig_1()
		throws Exception {
		GtnFrameworkParentIfpPopupConfig result = new GtnFrameworkParentIfpPopupConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkParentIfpPopupConfig fixture = new GtnFrameworkParentIfpPopupConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		// add additional test code here
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

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkParentIfpPopupConfigTest.class);
	}
}