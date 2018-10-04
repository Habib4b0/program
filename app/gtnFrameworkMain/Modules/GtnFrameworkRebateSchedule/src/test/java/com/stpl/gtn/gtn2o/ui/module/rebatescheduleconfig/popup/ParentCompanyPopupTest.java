package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class ParentCompanyPopupTest {
	
	@Test
	public void testParentCompanyPopup_1()
		throws Exception {
		ParentCompanyPopup result = new ParentCompanyPopup();
		assertNotNull(result);
	}

	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		ParentCompanyPopup fixture = new ParentCompanyPopup();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals(null, result.getViewActionList());
		assertEquals("parentCompanyView", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isResetAllowed());
		assertEquals("Parent Company", result.getViewName());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(false, result.isDefaultView());
	}


}