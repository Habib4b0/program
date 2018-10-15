package com.stpl.gtn.gtn2o.ui.module.cfp.dynamicclasses;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;



@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnUIFrameworkIfpDynamicClassFillerTest {

	
	
	@Test
	public void testAddDynamicObject() {
	GtnUIFrameworkCfpDynamicClassFiller instance = new GtnUIFrameworkCfpDynamicClassFiller();
    
    PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
    instance.addDynamicObject();
	}
}
