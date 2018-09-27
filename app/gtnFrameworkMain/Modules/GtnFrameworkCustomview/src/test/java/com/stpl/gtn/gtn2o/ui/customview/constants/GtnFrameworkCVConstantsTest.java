package com.stpl.gtn.gtn2o.ui.customview.constants;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCVConstantsTest {

	@Test
	public void testGetCvCustomPropertyIds_1()
		throws Exception {
		String[] result = GtnFrameworkCVConstants.getCvCustomPropertyIds();
		assertNotNull(result);
		assertEquals(5, result.length);
		assertEquals("customerViewScreenName", result[0]);
		assertEquals("customerRelation", result[1]);
		assertEquals("productRelation", result[2]);
		assertEquals("createdBy", result[3]);
		assertEquals("modifiedBy", result[4]);
	}


	@Test
	public void testGetCvListNameArrays_1()
		throws Exception {
		String[] result = GtnFrameworkCVConstants.getCvListNameArrays();
		assertNotNull(result);
		assertEquals(5, result.length);
		assertEquals("CV_MODULE_TYPE", result[0]);
		assertEquals("customerRelation", result[1]);
		assertEquals("productRelation", result[2]);
		assertEquals("USERS", result[3]);
		assertEquals("USERS", result[4]);
	}


}