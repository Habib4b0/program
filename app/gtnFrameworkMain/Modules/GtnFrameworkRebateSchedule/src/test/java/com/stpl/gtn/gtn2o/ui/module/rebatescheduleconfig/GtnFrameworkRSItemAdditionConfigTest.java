package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;


public class GtnFrameworkRSItemAdditionConfigTest {
	
	@Test
	public void testGtnFrameworkRSItemAdditionConfig_1()
		throws Exception {
		GtnFrameworkRSItemAdditionConfig result = new GtnFrameworkRSItemAdditionConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testAddItemAdditionTab_1()
		throws Exception {
		GtnFrameworkRSItemAdditionConfig fixture = new GtnFrameworkRSItemAdditionConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addItemAdditionTab(componentList);

	}


	
}