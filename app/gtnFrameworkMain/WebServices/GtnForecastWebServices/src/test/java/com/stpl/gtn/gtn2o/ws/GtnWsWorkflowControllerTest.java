/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.controller.GtnWsReturnsWorkflowController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 *
 * @author Nimisha.Rakesh
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationServiceContext-ReturnsForecasting.xml" })
@Ignore
public class GtnWsWorkflowControllerTest {

	@Autowired
	GtnWsReturnsWorkflowController gtnWsReturnsWorkflowController;

	@Test
	public void testWorkflowReturns() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest pMasterRequest = new GtnWsForecastRequest();
		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setProjectionMasterSid("21237");
		gtnWsRequest.setGtnWsForecastRequest(pMasterRequest);
		gtnWsRequest.getGtnWsForecastRequest().setGtnForecastBean(gtnForecastBean);
		gtnWsReturnsWorkflowController.getDetailsofProjectionForWorkflow(gtnWsRequest);
	}

}
