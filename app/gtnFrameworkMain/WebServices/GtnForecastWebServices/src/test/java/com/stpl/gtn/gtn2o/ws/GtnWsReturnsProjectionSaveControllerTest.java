package com.stpl.gtn.gtn2o.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.controller.GtnWsProjectionSaveController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationServiceContext-ReturnsForecasting.xml" })
@Ignore
public class GtnWsReturnsProjectionSaveControllerTest {
	@Autowired
	GtnWsProjectionSaveController gtnWsProjectionSaveController;

	@Ignore
	public void testsaveController() throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		SimpleDateFormat dateParse = new SimpleDateFormat("yyyy-MM-dd");
		GtnForecastBean pmRequest = new GtnForecastBean();
		pmRequest.setProjectionName("Test431");
		pmRequest.setProjectionDescription("Test431");
		pmRequest.setModuleName("returns");
		pmRequest.setTestFilePath("_TEST");
		try {
			pmRequest.setProjectionStartDate(dateParse.parse("2016-04-01"));
			pmRequest.setProjectionEndDate(dateParse.parse("2017-01-01 00:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		gtnWsRequest = configureInput(gtnWsRequest, pmRequest);
		testsaveProjection(gtnWsRequest);
		testviewSave(gtnWsRequest);

	}

	private void testsaveProjection(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		gtnWsProjectionSaveController.saveProjectionMaster(gtnWsRequest);
	}

	private void testviewSave(GtnUIFrameworkWebserviceRequest gtnWsRequest) throws GtnFrameworkGeneralException {
		gtnWsProjectionSaveController.saveViewMaster(gtnWsRequest);
	}

	private GtnUIFrameworkWebserviceRequest configureInput(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnForecastBean pmRequest) {
		List<String> inputHierarchy = new ArrayList<>();
		pmRequest.setCompanyMasterSid(46124);
		pmRequest.setProductHierarchySid(76);
		pmRequest.setProductHierarchyLevel(2);
		pmRequest.setProductHierarchyVersionNo(1);
		pmRequest.setProductHierarchyInnerLevel(2);
		pmRequest.setProdRelationshipBuilderSid(116);
		pmRequest.setCreatedBy(767497);
		pmRequest.setForecastType("Actuals");
		pmRequest.setHistory("4");
		pmRequest.setProjectionPeriodOrder("Ascending");
		pmRequest.setFrequency("Quarterly");
		inputHierarchy.add("116-1.");
		inputHierarchy.add("116-1.4.");
		inputHierarchy.add("116-1.4.1.");
		inputHierarchy.add("116-1.4.2.");
		inputHierarchy.add("116-1.4.3.");
		inputHierarchy.add("116-1.4.4.");
		inputHierarchy.add("116-1.4.5.");
		pmRequest.setRelationshipSidList(inputHierarchy);
		pmRequest.setUserId("767497");
		pmRequest.setForecastSessionId("11111");
		pmRequest.setViewName("Test091");
		pmRequest.setViewType("Public");
		GtnWsForecastRequest pMasterRequest = new GtnWsForecastRequest();
		pMasterRequest.setGtnForecastBean(pmRequest);
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);
		return gtnWsRequest;
	}

}
