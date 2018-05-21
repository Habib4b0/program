package com.stpl.gtn.gtn2o.ws.controller;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.service.GtnWsReportDashboardFilterOptionService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnReport-SpringContext-Test.xml" })
public class GtnWsReportDashboardFilterOptionTest {
	@Autowired
	private GtnWsReportDashboardFilterOptionService reportDashboardFilterService;

	@Test
	public void getCustAndProdLevelValues() {
		GtnWsReportDashboardBean gtnWsReportDashboardBean = new GtnWsReportDashboardBean();
		gtnWsReportDashboardBean.setHierarchyType(GtnWsHierarchyType.CUSTOMER);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setCustomerHierarchyForecastLevel(3);
		dataSelectionBean.setCustomerHierarchySid(11);

		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		reportRequest.setGtnWsReportDashboardBean(gtnWsReportDashboardBean);

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		gtnWsRequest.setGtnWsReportRequest(reportRequest);

		try {
			List<Object[]> resultList = reportDashboardFilterService.getCustAndProdLevelValues(gtnWsRequest);
			System.out.println(resultList.size());
		} catch (GtnFrameworkGeneralException e) {
			e.printStackTrace();
		}
	}

}
