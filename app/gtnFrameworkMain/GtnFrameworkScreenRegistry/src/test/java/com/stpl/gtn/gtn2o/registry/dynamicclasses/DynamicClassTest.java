package com.stpl.gtn.gtn2o.registry.dynamicclasses;

import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.registry.dynamicclasses.GtnUIFrameworkDynamicClassFiller;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class DynamicClassTest {

	
	@Test
	public void dynamicClassFillerTest()
	{
		 System.out.println("Dynamic class filler");
		 GtnUIFrameworkDynamicClassFiller instance = new GtnUIFrameworkDynamicClassFiller();
		 
		 PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		 instance.addDynamicObject();
		 verifyStatic(times(17));
	}
}
