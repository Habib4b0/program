package com.stpl.gtn.gtn2o.ws.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.service.GtnReportVariableDescriptionIndicatorService;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportRightTableLoadDataService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReport-SpringContext-Test.xml" })
public class GtnWsReportRightTableDataGenerationTest {
	@Autowired
	GtnWsReportRightTableLoadDataService service;
	
	@Autowired
	GtnReportVariableDescriptionIndicatorService indicatorService;

//	@Test
	public void testPRocedureOutput() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsReportRequest(new GtnWsReportRequest());
		request.getGtnWsReportRequest().setDataSelectionBean(new GtnWsReportDataSelectionBean());
		request.getGtnWsReportRequest().getDataSelectionBean().setSessionId("d01b97cd_2dc6_46");
		// System.out.println(service.getDataFromBackend(request));
	}

	@Test
	public void testHierarchyIndicator() {
	}
}
