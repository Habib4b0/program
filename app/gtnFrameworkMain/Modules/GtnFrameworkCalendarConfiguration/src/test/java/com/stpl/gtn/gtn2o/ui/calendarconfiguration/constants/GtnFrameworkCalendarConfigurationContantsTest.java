package com.stpl.gtn.gtn2o.ui.calendarconfiguration.constants;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author praveen.kumar
 */
public class GtnFrameworkCalendarConfigurationContantsTest {
	@Test
	public void testGetCalendarConfColumn_1()
		throws Exception {

		String[] result = GtnFrameworkCalendarConfigurationContants.getCalendarConfColumn();
		assertNotNull(result);
	}

	@Test
	public void testGetCalendarConfColumnType_1()
		throws Exception {

		Class<Object>[] result = (Class<Object>[]) GtnFrameworkCalendarConfigurationContants.getCalendarConfColumnType();
		assertNotNull(result);
	}
	@Test
	public void testGetCalendarConfHeader_1()
		throws Exception {

		String[] result = GtnFrameworkCalendarConfigurationContants.getCalendarConfHeader();
		assertNotNull(result);
	}

	@Test
	public void testGetCurrentYear_1()
		throws Exception {
		int result = GtnFrameworkCalendarConfigurationContants.getCurrentYear();
	}

	@Test
	public void testGetFieldList_1()
		throws Exception {
		List<String> result = GtnFrameworkCalendarConfigurationContants.getFieldList();
		assertNotNull(result);
	}

	@Test
	public void testGetTotalFieldList_1()
		throws Exception {
		List<String> result = GtnFrameworkCalendarConfigurationContants.getTotalFieldList();
		assertNotNull(result);
	}

	@Test
	public void testGetYearDdlbValue_1()
		throws Exception {
		List<String> result = GtnFrameworkCalendarConfigurationContants.getYearDdlbValue();
		assertNotNull(result);
	}
}