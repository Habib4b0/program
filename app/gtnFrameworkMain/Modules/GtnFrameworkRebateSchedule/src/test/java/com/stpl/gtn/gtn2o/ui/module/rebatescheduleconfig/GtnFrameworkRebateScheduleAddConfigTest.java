package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkRebateScheduleAddConfigTest {
	
	@Test
	public void testGtnFrameworkRebateScheduleAddConfig_1()
		throws Exception {
		GtnFrameworkRebateScheduleAddConfig result = new GtnFrameworkRebateScheduleAddConfig();
		assertNotNull(result);
	
	}

	
	@Test
	public void testGetAddView_1()
		throws Exception {
		GtnFrameworkRebateScheduleAddConfig fixture = new GtnFrameworkRebateScheduleAddConfig();

		GtnUIFrameworkViewConfig result = fixture.getAddView();

		
		assertNotNull(result);
		assertEquals("rebateScheduleAddView", result.getViewId());
		assertEquals(null, result.getViewActionList());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
		assertEquals(true, result.isResetAllowed());
		assertEquals("rebateScheduleAddView", result.getViewName());
		assertEquals(false, result.isReplicable());
	}

	
	@Test
	public void testGetResetFieldValueList_1()
		throws Exception {
		GtnFrameworkRebateScheduleAddConfig fixture = new GtnFrameworkRebateScheduleAddConfig();

		List<Object> result = fixture.getResetFieldValueList();

		
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	
	
}