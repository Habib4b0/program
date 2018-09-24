package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnFrameworkIfpLandingScreenConfigTest {

	@Test
	public void testGtnFrameworkIfpLandingScreenConfig_1()
		throws Exception {
		GtnFrameworkIfpLandingScreenConfig result = new GtnFrameworkIfpLandingScreenConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testGetDefaultFieldValueList_1()
		throws Exception {
		GtnFrameworkIfpLandingScreenConfig fixture = new GtnFrameworkIfpLandingScreenConfig();

		List<Object> result = fixture.getDefaultFieldValueList();

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testGetEnableCopyFieldValues_1()
		throws Exception {

		Object[] result = GtnFrameworkIfpLandingScreenConfig.getEnableCopyFieldValues();

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testGetSearchView_1()
		throws Exception {
		GtnFrameworkIfpLandingScreenConfig fixture = new GtnFrameworkIfpLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testGetVisibleIfpCopyFields_1()
		throws Exception {

		String[] result = GtnFrameworkIfpLandingScreenConfig.getVisibleIfpCopyFields();

		// add additional test code here
		assertNotNull(result);
	}

	@Test
	public void testSetEnableCopyFieldValues_1()
		throws Exception {
		Object[] enableCopyField = new Object[] {};

		GtnFrameworkIfpLandingScreenConfig.setEnableCopyFieldValues(enableCopyField);

		// add additional test code here
	}

	@Test
	public void testSetVisibleIfpCopyFields_1()
		throws Exception {
		String[] visibleCopyFields = new String[] {};

		GtnFrameworkIfpLandingScreenConfig.setVisibleIfpCopyFields(visibleCopyFields);

		// add additional test code here
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

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkIfpLandingScreenConfigTest.class);
	}
}