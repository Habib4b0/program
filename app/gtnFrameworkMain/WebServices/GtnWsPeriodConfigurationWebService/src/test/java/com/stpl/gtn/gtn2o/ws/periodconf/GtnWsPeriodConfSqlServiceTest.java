package com.stpl.gtn.gtn2o.ws.periodconf;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.gtn.gtn2o.ws.periodconf.controller.GtnWsPeriodConfigurationController;
import com.stpl.gtn.gtn2o.ws.periodconf.service.GtnWsPeriodConfigurationService;
import com.stpl.gtn.gtn2o.ws.periodconf.sqlservice.GtnWsPeriodConfSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnWsPeriodConfTest.xml" })
public class GtnWsPeriodConfSqlServiceTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Autowired
	private GtnWsPeriodConfSqlService periodConfSqlService;

	@Autowired
	private GtnWsPeriodConfigurationController gtnWsPeriodConfigurationController;

	@Autowired
	private GtnWsPeriodConfigurationService gtnWsPeriodConfigurationService;

	GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();

	@Test
	public void getQuery() {
		String sqlQuery = periodConfSqlService.getQuery("loadDate");
		System.out.println("query is--->" + sqlQuery);
	}

	@Test
	public void initTest() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		gtnWsPeriodConfigurationService.init();

		assertFalse(singletonObjectBean.getPeriodConfigResultList().get(0) == null);

	}

	@Before
	public void propertyTest() {
		System.setProperty("com.stpl.gtnframework.base.path", "E:/GTN Server Setup/conf");

	}

	@Test
	public void gtnWsPeriodConfSqlServiceExceptionTest() {

		periodConfSqlService.getQuery(null);

	}

	@Test
	public void controllerTest1() {

		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsPeriodConfigurationController.loadRefreshDate(gtnUIFrameworkWebserviceRequest);
		assertFalse(singletonObjectBean.getPeriodConfigResultList().get(0) == null);

	}

	@Test
	public void controllerTest2() {
		controllerTest1();
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsPeriodConfigurationController.loadDate(gtnUIFrameworkWebserviceRequest);
	}
	
	@Test
	public void sampleTest()
	{
		assertTrue(gtnWsPeriodConfigurationController.test());
	}

}
