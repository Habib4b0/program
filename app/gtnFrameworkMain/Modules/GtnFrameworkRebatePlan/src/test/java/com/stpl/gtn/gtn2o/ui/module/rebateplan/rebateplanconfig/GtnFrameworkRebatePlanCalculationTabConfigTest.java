package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;


public class GtnFrameworkRebatePlanCalculationTabConfigTest {

	@Test
	public void testGtnFrameworkRebatePlanCalculationTabConfig_1()
		throws Exception {
		GtnFrameworkRebatePlanCalculationTabConfig result = new GtnFrameworkRebatePlanCalculationTabConfig();
		assertNotNull(result);
	}
/*
	@Test
	public void testAddCalculationTab_1()
		throws Exception {
		GtnFrameworkRebatePlanCalculationTabConfig fixture = new GtnFrameworkRebatePlanCalculationTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new ChangeListenerCollection();

		fixture.addCalculationTab(componentList);

	}


	@Test
	public void testCalculationLayout_1()
		throws Exception {
		GtnFrameworkRebatePlanCalculationTabConfig fixture = new GtnFrameworkRebatePlanCalculationTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new ChangeListenerCollection();

		fixture.calculationLayout(componentList);
		}*/

	

	@Before
	public void setUp()
		throws Exception {
	}

	@After
	public void tearDown()
		throws Exception {
	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkRebatePlanCalculationTabConfigTest.class);
	}
}