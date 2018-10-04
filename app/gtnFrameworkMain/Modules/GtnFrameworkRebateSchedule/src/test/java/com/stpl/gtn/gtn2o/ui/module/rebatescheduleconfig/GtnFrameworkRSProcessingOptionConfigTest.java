package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnFrameworkRSProcessingOptionConfigTest {
	
	@Test
	public void testGtnFrameworkRSProcessingOptionConfig_1()
		throws Exception {
		GtnFrameworkRSProcessingOptionConfig result = new GtnFrameworkRSProcessingOptionConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testAddCompanyAdditionTab_1()
		throws Exception {
		GtnFrameworkRSProcessingOptionConfig fixture = new GtnFrameworkRSProcessingOptionConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addCompanyAdditionTab(componentList);

	}

	
}