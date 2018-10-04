package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnFrameworkRebateScheduleLandingScreenConfigTest {
	
	@Test
	public void testGtnFrameworkRebateScheduleLandingScreenConfig_1()
		throws Exception {
		GtnFrameworkRebateScheduleLandingScreenConfig result = new GtnFrameworkRebateScheduleLandingScreenConfig();
		assertNotNull(result);
	}


	@Test
	public void testGetDefaultFieldValueList_1()
		throws Exception {
		GtnFrameworkRebateScheduleLandingScreenConfig fixture = new GtnFrameworkRebateScheduleLandingScreenConfig();

		List<Object> result = fixture.getDefaultFieldValueList();

		assertNotNull(result);
		assertEquals(2, result.size());
	}


	@Test
	public void testGetEnableCopyField_1()
		throws Exception {

		Object[] result = GtnFrameworkRebateScheduleLandingScreenConfig.getEnableCopyField();

		assertNotNull(result);
	}

	
	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkRebateScheduleLandingScreenConfig fixture = new GtnFrameworkRebateScheduleLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		assertNotNull(result);
		assertEquals("V001", result.getViewId());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isResetAllowed());
		assertEquals("Search View", result.getViewName());
		assertEquals(true, result.isDefaultView());
	}

	
	@Test
	public void testGetViewDefaultFieldValueList_1()
		throws Exception {
		GtnFrameworkRebateScheduleLandingScreenConfig fixture = new GtnFrameworkRebateScheduleLandingScreenConfig();

		List<Object> result = fixture.getViewDefaultFieldValueList();

		assertNotNull(result);
		assertEquals(2, result.size());
	}


	@Test
	public void testGetVisibleCopyFields_1()
		throws Exception {

		String[] result = GtnFrameworkRebateScheduleLandingScreenConfig.getVisibleCopyFields();

		assertNotNull(result);
	}

	
	@Test
	public void testSetEnableCopyField_1()
		throws Exception {
		Object[] enableCopyField = new Object[] {};

		GtnFrameworkRebateScheduleLandingScreenConfig.setEnableCopyField(enableCopyField);

	}

	
	@Test
	public void testSetVisibleCopyFields_1()
		throws Exception {
		String[] visibleCopyFields = new String[] {};

		GtnFrameworkRebateScheduleLandingScreenConfig.setVisibleCopyFields(visibleCopyFields);

	}


}