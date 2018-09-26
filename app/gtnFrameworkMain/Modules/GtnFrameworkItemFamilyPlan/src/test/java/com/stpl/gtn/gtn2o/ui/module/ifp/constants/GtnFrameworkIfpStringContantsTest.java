package com.stpl.gtn.gtn2o.ui.module.ifp.constants;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@Ignore
public class GtnFrameworkIfpStringContantsTest {

	@Test
	public void testGeIfpVisibleColumnForView_1()
		throws Exception {

		Object[] result = GtnFrameworkIfpStringContants.geIfpVisibleColumnForView();
		assertNotNull(result);
	}

	@Test
	public void testGetCopyDisabledFields_1()
		throws Exception {

		Object[] result = GtnFrameworkIfpStringContants.getCopyDisabledFields();
		assertNotNull(result);
	}

	@Test
	public void testGetDateFieldPropertiesList_1()
		throws Exception {

		List<String> result = GtnFrameworkIfpStringContants.getDateFieldPropertiesList();
		assertNotNull(result);
	}

	@Test
	public void testGetIfpCustomPropertyIds_1()
		throws Exception {

		String[] result = GtnFrameworkIfpStringContants.getIfpCustomPropertyIds();
		assertNotNull(result);
	}

	@Test
	public void testGetIfpListNameArray_1()
		throws Exception {

		String[] result = GtnFrameworkIfpStringContants.getIfpListNameArray();
		assertNotNull(result);
	}

	@Test
	public void testGetIfpVisibleHeaderForView_1()
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

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnFrameworkIfpStringContantsTest.class);
	}
}