package com.stpl.gtn.gtn2o.ws;
/**
 *
 * @author Nimisha.Rakesh
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.controller.GtnWsReturnsEditController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationServiceContext-ReturnsForecasting.xml" })
@Ignore
public class GtnWsReturnsEditControllerTest {

	@Autowired
	GtnWsReturnsEditController gtnWsReturnsEditController;

	@Test
	public void testEditReturns()
			throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		Map<String, List<String>> map = new HashMap<>(2);
		List<String> actualQueryInputs = new ArrayList<>(3);
		GtnWsForecastRequest pMasterRequest = new GtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setProjectionMasterSid("21237");
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);
		gtnWsRequest.getGtnWsForecastRequest().setGtnForecastBean(gtnForecastBean);
		try {
			gtnWsReturnsEditController.getProjectionSelectionDetails(gtnWsRequest);
		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}
		String hierarchy = "('116-1.'),('116-1.4.'),('116-1.4.1.'),('116-1.4.2.'),('116-1.4.3.'),('116-1.4.4.'),('116-1.4.5.')";
		actualQueryInputs.add(hierarchy);
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.set(Calendar.YEAR, 2014);
		startDateCal.set(Calendar.MONTH, Calendar.JANUARY);
		startDateCal.set(Calendar.DAY_OF_MONTH, 1);
		Date startDate = startDateCal.getTime();
		Calendar endDateCal = Calendar.getInstance();
		endDateCal.set(Calendar.YEAR, 2017);
		endDateCal.set(Calendar.MONTH, Calendar.JANUARY);
		endDateCal.set(Calendar.DAY_OF_MONTH, 1);
		Date endDate = endDateCal.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		actualQueryInputs.add(simpleDateFormat.format(startDate));
		actualQueryInputs.add(simpleDateFormat.format(endDate));
		map.put("RETURNS_FORECAST_ACTUAL_EDIT", actualQueryInputs);
		gtnForecastBean.setQueryParameters(map);
		gtnForecastBean.setMode("EDIT");
		gtnForecastBean.setUserId("767497");
		gtnForecastBean.setTestFilePath("_TEST");
		gtnForecastBean.setForecastSessionId("11111");
		gtnForecastBean.setModuleName("RETURNS");
		gtnForecastBean.setViewType("Public");
		gtnForecastBean.setForecastStartDate(startDate);
		gtnForecastBean.setForecastEndDate(endDate);
		gtnForecastBean.setProjectionMasterSid("21237");
		gtnForecastBean.setRelationshipBuilderSid(116);
		gtnForecastBean.setForecastLevel(2);
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);
		gtnWsRequest.getGtnWsForecastRequest().setGtnForecastBean(gtnForecastBean);
		gtnWsReturnsEditController.createFileForEditMode(gtnWsRequest);

		getInfoAboutView(gtnWsRequest);

	}

	private void getInfoAboutView(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		try {
			gtnWsReturnsEditController.getProjectionViewDetails(gtnWsRequest);
			gtnWsReturnsEditController.getExistingView(gtnWsRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
