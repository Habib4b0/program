package com.stpl.gtn.gtn2o.ws.periodconf;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.periodconf.controller.GtnWsPeriodConfigurationController;
import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnWsPeriodConfTest.xml" })
public class GtnWsPeriodConfSqlServiceTest {
	
	public GtnWsPeriodConfSqlServiceTest()
	{
		//nothing is here
	}

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Autowired
	private GtnWsPeriodConfSqlService periodConfSqlService;

	@Autowired
	private GtnWsPeriodConfigurationController gtnWsPeriodConfigurationController;

	@Test
	public void getQuery() {
		String actualSqlQuery = periodConfSqlService.getQuery("loadDate");
		String expectedSqlQuery = "SELECT FORECAST_CONFIG.FROM_DATE AS FROM_DATE, FORECAST_CONFIG.TO_DATE  as TO_DATE, HELPER_TABLE.DESCRIPTION FROM FORECAST_CONFIG JOIN HELPER_TABLE ON FORECAST_CONFIG.BUSINESS_PROCESS_TYPE=HELPER_TABLE.HELPER_TABLE_SID WHERE FORECAST_CONFIG.ACTIVE_END_DATE IS NULL"; 
		System.out.println("************"+expectedSqlQuery);
		System.out.println("************"+actualSqlQuery.trim());
		System.out.println("Result:"+actualSqlQuery.trim().equalsIgnoreCase(expectedSqlQuery));
		assertTrue(actualSqlQuery.trim().equalsIgnoreCase(expectedSqlQuery));
	}
	
	@Test
	public void sampleTestPost()
	{
		assertTrue(gtnWsPeriodConfigurationController.testPost());
	}
	@Test
	public void sampleTestGet()
	{
		assertTrue(gtnWsPeriodConfigurationController.testGet());
	}

}
