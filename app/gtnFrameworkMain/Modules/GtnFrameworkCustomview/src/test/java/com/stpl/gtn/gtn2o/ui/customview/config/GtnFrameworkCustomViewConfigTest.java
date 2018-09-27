package com.stpl.gtn.gtn2o.ui.customview.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCustomViewConfigTest {

	@Test
	public void testGetCustomViewRootConfig_1()
		throws Exception {
		GtnFrameworkCustomViewConfig fixture = new GtnFrameworkCustomViewConfig();
		GtnUIFrameworkRootConfig result = fixture.getCustomViewRootConfig();
		assertNotNull(result);
	}
}