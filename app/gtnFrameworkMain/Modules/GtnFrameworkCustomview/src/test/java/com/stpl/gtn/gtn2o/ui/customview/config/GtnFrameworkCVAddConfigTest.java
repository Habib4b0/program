package com.stpl.gtn.gtn2o.ui.customview.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCVAddConfigTest {

	@Test
	public void testGtnFrameworkCVAddConfig_1()
		throws Exception {
		GtnFrameworkCVAddConfig result = new GtnFrameworkCVAddConfig();
		assertNotNull(result);
	}


	@Test
	public void testGetCustomViewAddView_1()
		throws Exception {
		GtnFrameworkCVAddConfig fixture = new GtnFrameworkCVAddConfig();
		GtnUIFrameworkViewConfig result = fixture.getCustomViewAddView();
		assertNotNull(result);
		assertEquals("V002", result.getViewId());
		assertEquals(false, result.isDefaultView());
		assertEquals(true, result.isResetAllowed());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isReplicable());
		assertEquals("Add View", result.getViewName());
		assertEquals(null, result.getViewActionList());
	}

}