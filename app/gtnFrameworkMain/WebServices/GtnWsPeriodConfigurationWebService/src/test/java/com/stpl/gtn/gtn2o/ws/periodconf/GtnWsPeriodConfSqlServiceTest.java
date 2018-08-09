package com.stpl.gtn.gtn2o.ws.periodconf;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnWsPeriodConfTest.xml" })
public class GtnWsPeriodConfSqlServiceTest {

	@Autowired
	private GtnWsPeriodConfSqlService periodConfSqlService;

	@Test
	public void getQuery() {
		String sqlQuery = periodConfSqlService.getQuery("loadDate");
		System.out.println("query is--->" + sqlQuery);
	}

}
