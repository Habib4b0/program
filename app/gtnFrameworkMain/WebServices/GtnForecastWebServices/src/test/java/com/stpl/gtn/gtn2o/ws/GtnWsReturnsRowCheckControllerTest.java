package com.stpl.gtn.gtn2o.ws;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.controller.GtnWsReturnsRowCheckController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationServiceContext-ReturnsForecasting.xml" })
@Ignore
public class GtnWsReturnsRowCheckControllerTest {
	@Autowired
	GtnWsReturnsRowCheckController checkController;

	@Ignore
	public void testUpdateCheckInFile() throws FileNotFoundException, ClassNotFoundException, IOException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsForecastRequest pMasterRequest = new GtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setSelectedHierarchyNo("116-1.");
		gtnForecastBean.setCheckValue("true");
		pMasterRequest.setGtnForecastBean(gtnForecastBean);
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);
		checkController.updateCheckInFile(gtnWsRequest);

	}

}
