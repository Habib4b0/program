package com.stpl.gtn.gtn2o.ui.module.udc.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

// @author praveen.kumar

public class GtnFrameworkUDCConfigurationConfigTest {

	@Test
	public void testGetUDCConfigurationRootConfig_1()
		throws Exception {
		GtnFrameworkUDCConfigurationConfig fixture = new GtnFrameworkUDCConfigurationConfig();
		GtnUIFrameworkRootConfig result = fixture.getUDCConfigurationRootConfig();
		assertNotNull(result);
	}

}