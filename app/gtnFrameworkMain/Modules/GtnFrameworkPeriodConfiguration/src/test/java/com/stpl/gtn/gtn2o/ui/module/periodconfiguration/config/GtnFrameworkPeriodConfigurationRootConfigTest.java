package com.stpl.gtn.gtn2o.ui.module.periodconfiguration.config;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkPeriodConfigurationRootConfigTest {

	@Test
	public void testGetPeriodConfigurationRootConfig_1()
		throws Exception {
		GtnFrameworkPeriodConfigurationRootConfig fixture = new GtnFrameworkPeriodConfigurationRootConfig();
		GtnUIFrameworkRootConfig result = fixture.getPeriodConfigurationRootConfig();
		assertNotNull(result);
	}

}