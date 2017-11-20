package com.stpl.gtn.gtn2o.ws.calendarconfiguration;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.module.calendarconfiguration.controller.GtnWsCalendarConfigurationController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.calendarconfiguration.GtnWsCalendarConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GTNRestController-servlet.xml" })

public class GtnWsCalendarConfigurationWebServiceTest {

	@Autowired
	private GtnWsCalendarConfigurationController gtnWsCalendarConfigurationController;

	@BeforeClass
	public static void setUpClass() {
		return;
	}

	@AfterClass
	public static void tearDownClass() {
		return;
	}

	@Before
	public void setUp() {
		return;
	}

	@After
	public void tearDown() {
		return;
	}

	public GtnWsCalendarConfigurationController getGtnWsCalendarConfigurationController() {
		return gtnWsCalendarConfigurationController;
	}

	public void setGtnWsCalendarConfigurationController(
			GtnWsCalendarConfigurationController gtnWsCalendarConfigurationController) {
		this.gtnWsCalendarConfigurationController = gtnWsCalendarConfigurationController;
	}

	private GtnUIFrameworkWebserviceRequest getWsRequest() {
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(null);

		generalWSRequest.setUserId("10948");
		generalWSRequest.setSessionId(String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)));
		serviceRequest.setGtnWsGeneralRequest(generalWSRequest);

		request.setGtnWebServiceSearchCriteriaList(new ArrayList<GtnWebServiceSearchCriteria>());
		request.setGtnWebServiceOrderByCriteriaList(new ArrayList<GtnWebServiceOrderByCriteria>());
		serviceRequest.setGtnWsSearchRequest(request);
		return serviceRequest;
	}

	@Test
	public void testGetCalendarConfigurationTableData() {
		System.out.println("inside testGetCalendarConfigurationTableData");
		GtnUIFrameworkWebserviceRequest serviceRequest = getWsRequest();
		serviceRequest.getGtnWsSearchRequest().setCount(true);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsCalendarConfigurationController()
				.getCalendarConfigurationTableData(serviceRequest);
		System.out.println("record count = " + serviceResponse.getGtnSerachResponse().getCount());
		if (serviceResponse.getGtnSerachResponse().getCount() > 0) {
			int offset = Math.min(5, serviceResponse.getGtnSerachResponse().getCount());
			System.out.println("going to fetch first " + offset + " record");
			serviceRequest.getGtnWsSearchRequest().setCount(false);
			serviceRequest.getGtnWsSearchRequest().setTableRecordStart(0);
			serviceRequest.getGtnWsSearchRequest().setTableRecordOffset(offset);
			serviceResponse = getGtnWsCalendarConfigurationController()
					.getCalendarConfigurationTableData(serviceRequest);
			for (GtnUIFrameworkDataRow record : serviceResponse.getGtnSerachResponse().getResultSet().getDataTable()) {
				System.out.println("record= " + record.getColList());
			}
		}
	}

	@Test
	public void testLoadCalendarConfiguration() {
		System.out.println("inside testLoadCalendarConfiguration");
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCalendarConfigurationRequest ccRequest = new GtnWsCalendarConfigurationRequest();
		serviceRequest.setCalendarConfigurationRequest(ccRequest);
		ccRequest.setCalendarId(1);
		GtnUIFrameworkWebserviceResponse serviceResponse = getGtnWsCalendarConfigurationController()
				.loadCalendarConfiguration(serviceRequest);
		System.out.println("holidays are " + serviceResponse.getCalendarConfigurationResponse().getHolidays());
	}

}
