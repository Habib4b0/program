package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkIfpLandingScreenConfigTest {

	@Test
	public void testGtnFrameworkIfpLandingScreenConfig()
		throws Exception {
		GtnFrameworkIfpLandingScreenConfig result = new GtnFrameworkIfpLandingScreenConfig();
		assertNotNull(result);
		// add additional test code here
	}

	@Test
	public void testGetDefaultFieldValueList()
		throws Exception {
		GtnFrameworkIfpLandingScreenConfig fixture = new GtnFrameworkIfpLandingScreenConfig();

		List<Object> result = fixture.getDefaultFieldValueList();

		assertNotNull(result);
	}

	@Test
	public void testGetEnableCopyFieldValues()
		throws Exception {
		
		Object[] enableCopyField = new Object[] {"1", "2"};
		GtnFrameworkIfpLandingScreenConfig.setEnableCopyFieldValues(enableCopyField);
		
		Object[] result = GtnFrameworkIfpLandingScreenConfig.getEnableCopyFieldValues();
		assertNotNull(result);
	}

	@Test
	public void testGetSearchView()
		throws Exception {
		GtnFrameworkIfpLandingScreenConfig fixture = new GtnFrameworkIfpLandingScreenConfig();

		GtnUIFrameworkViewConfig result = fixture.getSearchView();
		assertNotNull(result);
	}

	@Test
	public void testGetVisibleIfpCopyFields()
		throws Exception {

		String[] result = GtnFrameworkIfpLandingScreenConfig.getVisibleIfpCopyFields();
		assertNotNull(result);
	}

	@Test
	public void testSetEnableCopyFieldValues()
		throws Exception {
		Object[] enableCopyField = new Object[] {};
		GtnFrameworkIfpLandingScreenConfig.setEnableCopyFieldValues(enableCopyField);
	}

	@Test
	public void testSetVisibleIfpCopyFields()
		throws Exception {
		String[] visibleCopyFields = new String[] {};
		GtnFrameworkIfpLandingScreenConfig.setVisibleIfpCopyFields(visibleCopyFields);
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