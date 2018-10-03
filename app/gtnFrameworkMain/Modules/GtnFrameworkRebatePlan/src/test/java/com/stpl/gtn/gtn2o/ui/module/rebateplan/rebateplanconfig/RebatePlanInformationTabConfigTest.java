package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;


public class RebatePlanInformationTabConfigTest {
	
	@Test
	public void testRebatePlanInformationTabConfig_1()
		throws Exception {
		RebatePlanInformationTabConfig result = new RebatePlanInformationTabConfig();
		assertNotNull(result);
	}

/*
	@Test
	public void testAddCompanyInformationTab_1()
		throws Exception {
		RebatePlanInformationTabConfig fixture = new RebatePlanInformationTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new ChangeListenerCollection();

		fixture.addCompanyInformationTab(componentList);

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
		new org.junit.runner.JUnitCore().run(RebatePlanInformationTabConfigTest.class);
	}
}