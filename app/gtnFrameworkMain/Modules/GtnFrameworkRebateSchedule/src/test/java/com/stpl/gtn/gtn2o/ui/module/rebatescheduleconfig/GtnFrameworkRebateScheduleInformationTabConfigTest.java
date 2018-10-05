package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;


public class GtnFrameworkRebateScheduleInformationTabConfigTest {
	
	@Test
	public void testGtnFrameworkRebateScheduleInformationTabConfig_1()
		throws Exception {
		GtnFrameworkRebateScheduleInformationTabConfig result = new GtnFrameworkRebateScheduleInformationTabConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testAddrebateScheduleInfoTab_1()
		throws Exception {
		GtnFrameworkRebateScheduleInformationTabConfig fixture = new GtnFrameworkRebateScheduleInformationTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addrebateScheduleInfoTab(componentList);

	}

	
}