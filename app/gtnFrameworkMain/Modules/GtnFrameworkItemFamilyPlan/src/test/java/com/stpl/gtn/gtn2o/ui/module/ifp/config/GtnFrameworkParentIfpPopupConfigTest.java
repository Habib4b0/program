package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkParentIfpPopupConfigTest {
	
	@Test
	public void testGtnFrameworkParentIfpPopupConfig()
		throws Exception {
		
		GtnFrameworkParentIfpPopupConfig result = new GtnFrameworkParentIfpPopupConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetSearchView()
		throws Exception {
		
		GtnFrameworkParentIfpPopupConfig fixture = new GtnFrameworkParentIfpPopupConfig();
		GtnUIFrameworkViewConfig result = fixture.getSearchView();
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
}