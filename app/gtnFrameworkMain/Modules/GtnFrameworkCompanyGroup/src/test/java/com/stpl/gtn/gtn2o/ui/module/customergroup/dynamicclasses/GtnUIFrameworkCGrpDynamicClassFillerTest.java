package com.stpl.gtn.gtn2o.ui.module.customergroup.dynamicclasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnUIFrameworkCGrpDynamicClassFillerTest {

	@Test
	public void testAddDynamicObject(){
		GtnUIFrameworkCGrpDynamicClassFiller instance = new GtnUIFrameworkCGrpDynamicClassFiller();

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		instance.addDynamicObject();

		
	}

}