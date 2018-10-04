package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;


public class GtnFrameworkRebateScheduleConfigTest {
	
	@Test
	public void testGetRebateScheduleRootConfig_1()
		throws Exception {
		GtnFrameworkRebateScheduleConfig fixture = new GtnFrameworkRebateScheduleConfig();

		GtnUIFrameworkRootConfig result = fixture.getRebateScheduleRootConfig();

		
		assertNotNull(result);
	}



	
	
}