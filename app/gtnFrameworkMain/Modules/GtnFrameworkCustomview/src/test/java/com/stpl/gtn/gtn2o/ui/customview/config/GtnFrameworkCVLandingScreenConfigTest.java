package com.stpl.gtn.gtn2o.ui.customview.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCVLandingScreenConfigTest {

	@Test
	public void testGtnFrameworkCVLandingScreenConfig_1()
		throws Exception {
		GtnFrameworkCVLandingScreenConfig result = new GtnFrameworkCVLandingScreenConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkCVLandingScreenConfig fixture = new GtnFrameworkCVLandingScreenConfig();
		GtnUIFrameworkViewConfig result = fixture.getSearchView();
		assertNotNull(result);
		assertEquals("CVSearch", result.getViewId());
		assertEquals(true, result.isDefaultView());
		assertEquals(true, result.isResetAllowed());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isReplicable());
		assertEquals("CVSearch", result.getViewName());
		assertEquals(null, result.getViewActionList());
	}

}