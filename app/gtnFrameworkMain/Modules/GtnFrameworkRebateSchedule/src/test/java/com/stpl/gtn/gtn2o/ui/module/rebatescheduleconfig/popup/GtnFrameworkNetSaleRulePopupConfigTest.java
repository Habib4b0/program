package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkNetSaleRulePopupConfigTest {
	
	@Test
	public void testGtnFrameworkNetSaleRulePopupConfig_1()
		throws Exception {
		GtnFrameworkNetSaleRulePopupConfig result = new GtnFrameworkNetSaleRulePopupConfig();
		assertNotNull(result);
	}


	@Test
	public void testGetView_1()
		throws Exception {
		GtnFrameworkNetSaleRulePopupConfig fixture = new GtnFrameworkNetSaleRulePopupConfig();

		GtnUIFrameworkViewConfig result = fixture.getView();

		assertNotNull(result);
		assertEquals("RSNSRuleView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("RSNSRuleView", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}


}