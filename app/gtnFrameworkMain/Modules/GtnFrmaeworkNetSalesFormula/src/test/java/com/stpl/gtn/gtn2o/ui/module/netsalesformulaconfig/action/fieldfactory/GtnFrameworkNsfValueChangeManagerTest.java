package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory;

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
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class})
public class GtnFrameworkNsfValueChangeManagerTest {
	
	
	@Test
	public void testIsValueChangeAllowed_else()
		throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		PowerMockito.when(GtnUIFrameworkGlobalUI.getSessionProperty("ValueChangeAllowed")).thenReturn(true);
		
		Boolean result = GtnFrameworkNsfValueChangeManager.isValueChangeAllowed();
		assertTrue(result);
	}
	
	@Test
	public void testIsValueChangeAllowed_if()
		throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		PowerMockito.when(GtnUIFrameworkGlobalUI.getSessionProperty("ValueChangeAllowed")).thenReturn(null);
		GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", true);
		
		GtnFrameworkNsfValueChangeManager.isValueChangeAllowed();
	}
	
	@Test
	public void testSetValueChangeAllowed()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUIFrameworkGlobalUI.addSessionProperty("ValueChangeAllowed", new Object());
		GtnFrameworkNsfValueChangeManager.setValueChangeAllowed(true);
	}
	
	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}