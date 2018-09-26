package com.stpl.gtn.gtn2o.ui.module.ifp.constants;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

public class GtnFrameworkIfpStringContantsTest {

	@Test
	public void testGeIfpVisibleColumnForView()
		throws Exception {

		Object[] result = GtnFrameworkIfpStringContants.geIfpVisibleColumnForView();
		assertNotNull(result);
	}

	@Test
	public void testGetCopyDisabledFields()
		throws Exception {

		Object[] result = GtnFrameworkIfpStringContants.getCopyDisabledFields();
		assertNotNull(result);
	}

	@Test
	public void testGetDateFieldPropertiesList()
		throws Exception {

		List<String> result = GtnFrameworkIfpStringContants.getDateFieldPropertiesList();
		assertNotNull(result);
	}

	@Test
	public void testGetIfpCustomPropertyIds()
		throws Exception {

		String[] result = GtnFrameworkIfpStringContants.getIfpCustomPropertyIds();
		assertNotNull(result);
	}

	@Test
	public void testGetIfpListNameArray()
		throws Exception {

		String[] result = GtnFrameworkIfpStringContants.getIfpListNameArray();
		assertNotNull(result);
	}

	@Test
	public void testGetIfpVisibleHeaderForView()
		throws Exception {

		String[] result = GtnFrameworkIfpStringContants.getIfpVisibleHeaderForView();
		assertNotNull(result);
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