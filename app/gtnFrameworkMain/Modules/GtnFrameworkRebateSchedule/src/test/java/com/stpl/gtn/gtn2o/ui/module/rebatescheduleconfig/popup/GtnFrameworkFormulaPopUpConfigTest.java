package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkFormulaPopUpConfigTest {
	
	@Test
	public void testGtnFrameworkFormulaPopUpConfig_1()
		throws Exception {
		GtnFrameworkFormulaPopUpConfig result = new GtnFrameworkFormulaPopUpConfig();
		assertNotNull(result);
	}

	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkFormulaPopUpConfig fixture = new GtnFrameworkFormulaPopUpConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("FormulaPopUpSearchSearchView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("FormulaPopUpSearchSearchView", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}


}