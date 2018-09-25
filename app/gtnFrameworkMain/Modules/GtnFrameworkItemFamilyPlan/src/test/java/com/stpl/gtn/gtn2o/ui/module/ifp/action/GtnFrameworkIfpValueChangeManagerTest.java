package com.stpl.gtn.gtn2o.ui.module.ifp.action;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
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
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class})
public class GtnFrameworkIfpValueChangeManagerTest {

	@Test
	public void testIsValueChangeAllowed()
		throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		PowerMockito.when(GtnUIFrameworkGlobalUI.getSessionProperty("ValueChangeAllowed")).thenReturn(true);
		
		Boolean result = GtnFrameworkIfpValueChangeManager.isValueChangeAllowed();
		assertTrue(result);
	}

	@Test
	public void testSetValueChangeAllowed()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(true);
	}

	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}