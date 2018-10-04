package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;


public class GtnFrameworkRebatePlanAddConfigTest {

	@Test
	public void testGtnFrameworkRebatePlanAddConfig_1()
		throws Exception {
		GtnFrameworkRebatePlanAddConfig result = new GtnFrameworkRebatePlanAddConfig();
		assertNotNull(result);
	}


	@Test
	public void testGetAddView_1()
		throws Exception {
		GtnFrameworkRebatePlanAddConfig fixture = new GtnFrameworkRebatePlanAddConfig();

		GtnUIFrameworkViewConfig result = fixture.getAddView();

		assertNotNull(result);
		assertEquals("rebatePlanAddView", result.getViewId());
		assertEquals(false, result.isDefaultView());
		assertEquals(null, result.getViewActionList());
		assertEquals("rebatePlanAddView", result.getViewName());
		assertEquals(false, result.isReplicable());
		assertEquals(true, result.isAddToNavigator());
		assertEquals(true, result.isResetAllowed());
	}


	@Before
	public void setUp()
		throws Exception {
	}

	
	@After
	public void tearDown()
		throws Exception {
	}


	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkRebatePlanAddConfigTest.class);
	}
}