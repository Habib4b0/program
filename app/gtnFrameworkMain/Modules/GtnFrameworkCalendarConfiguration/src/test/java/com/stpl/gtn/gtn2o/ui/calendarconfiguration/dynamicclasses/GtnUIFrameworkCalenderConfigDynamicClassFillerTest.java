package com.stpl.gtn.gtn2o.ui.calendarconfiguration.dynamicclasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

/**
 * @author praveen.kumar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnUIFrameworkCalenderConfigDynamicClassFillerTest {
	
	@Test
	public void testAddDynamicObject_1()
		throws Exception {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkCalenderConfigDynamicClassFiller fixture = new GtnUIFrameworkCalenderConfigDynamicClassFiller();
		fixture.addDynamicObject();
	}
}