package com.stpl.gtn.gtn2o.ui.module.ifp.dynamicclasses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnUIFrameworkIfpDynamicClassFillerTest {

		
		@Test
		public void testAddDynamicObject() {
			System.out.println("addDynamicObject");
			GtnUIFrameworkIfpDynamicClassFiller instance = new GtnUIFrameworkIfpDynamicClassFiller();
	        
	        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
	        instance.addDynamicObject();
	        
	        //verifyStatic(1);
		}


}